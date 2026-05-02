package com.quanta.content.item.tool;

import com.quanta.content.block.cable.CableBlock;
import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.transfer.ConnectionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;

import java.util.List;
import java.util.Optional;

public class QuantumWrenchItem extends Item {

    public QuantumWrenchItem(Properties properties) { super(properties); }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        BlockState state = level.getBlockState(pos);
        BlockEntity be = level.getBlockEntity(pos);

        if (player == null || !(be instanceof CableBlockEntity cable)) return InteractionResult.PASS;
        if (level.isClientSide) return InteractionResult.SUCCESS;

        // Descobre qual conector foi clicado usando ray trace + hitbox
        Direction face = getClickedConnectorFace(player, pos, state);
        if (face == null) return InteractionResult.PASS;

        ConnectionConfig config = cable.getConfig(face);
        ConnectionConfig.Mode previousMode = config.getMode();

        if (player.isShiftKeyDown()) {
            // Shift + Click: toggle DISABLED
            if (config.getMode() == ConnectionConfig.Mode.DISABLED) {
                // Volta ao modo anterior (se tinha, senão INSERT)
                config.setMode(config.getPreviousMode());
                player.displayClientMessage(Component.literal("§aFace " + face + " reconectada (" + config.getMode() + ")"), true);
            } else {
                config.setMode(ConnectionConfig.Mode.DISABLED);
                player.displayClientMessage(Component.literal("§cFace " + face + " desconectada"), true);
            }
        } else if (config.getMode() != ConnectionConfig.Mode.DISABLED) {
            // Click normal: cicla INSERT ↔ EXTRACT (só se não estiver DISABLED)
            if (config.getMode() == ConnectionConfig.Mode.INSERT) {
                config.setMode(ConnectionConfig.Mode.EXTRACT);
                player.displayClientMessage(Component.literal("§6Face " + face + ": EXTRACT"), true);
            } else {
                config.setMode(ConnectionConfig.Mode.INSERT);
                player.displayClientMessage(Component.literal("§aFace " + face + ": INSERT"), true);
            }
        }

        cable.setConfig(face, config);
        cable.syncToClient();
        return InteractionResult.SUCCESS;
    }

    /**
     * Usa ray trace para descobrir qual conector (face) foi clicado.
     * Verifica se o ponto de colisão está dentro da hitbox do conector.
     */
    private Direction getClickedConnectorFace(Player player, BlockPos pos, BlockState state) {
        HitResult hit = player.pick(5.0, 0.0f, false);
        if (!(hit instanceof BlockHitResult blockHit)) return null;
        if (!blockHit.getBlockPos().equals(pos)) return null;

        // Ponto exato do clique no bloco (0 a 1 em cada eixo)
        Vec3 clickPos = blockHit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());

        // Verifica cada face que tem conexão ativa no blockstate
        for (Direction dir : Direction.values()) {
            if (state.getValue(CableBlock.DIR_PROPS[dir.ordinal()])) {
                if (isInsideConnector(clickPos, dir)) {
                    return dir;
                }
            }
        }

        // Se não acertou nenhum conector, retorna a face do bloco (fallback)
        return blockHit.getDirection();
    }

    /**
     * Verifica se o ponto de clique está dentro da hitbox do conector.
     * Conector: 2x2x8 nas bordas do bloco.
     */
    private boolean isInsideConnector(Vec3 click, Direction dir) {
        double min = 7.0 / 16.0;  // 0.4375
        double max = 9.0 / 16.0;  // 0.5625
        double near = 0.0;
        double far = 8.0 / 16.0;  // 0.5

        return switch (dir) {
            case NORTH -> click.x >= min && click.x <= max && click.y >= min && click.y <= max && click.z >= near && click.z <= far;
            case SOUTH -> click.x >= min && click.x <= max && click.y >= min && click.y <= max && click.z >= (1.0 - far) && click.z <= 1.0;
            case EAST  -> click.x >= (1.0 - far) && click.x <= 1.0 && click.y >= min && click.y <= max && click.z >= min && click.z <= max;
            case WEST  -> click.x >= near && click.x <= far && click.y >= min && click.y <= max && click.z >= min && click.z <= max;
            case UP    -> click.x >= min && click.x <= max && click.y >= (1.0 - far) && click.y <= 1.0 && click.z >= min && click.z <= max;
            case DOWN  -> click.x >= min && click.x <= max && click.y >= near && click.y <= far && click.z >= min && click.z <= max;
        };
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§7Click connector: §fCycle INSERT/EXTRACT"));
        tooltip.add(Component.literal("§7Shift+Click connector: §fDisable/Enable"));
        tooltip.add(Component.literal("§7Disabled connectors only reactivate with Shift+Click"));
    }
}
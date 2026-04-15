package com.quanta.item;

import com.quanta.component.ModDataComponents;
import com.quanta.experiment.ExperimentContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class QuantumTesterItem extends Item {
    
    private static final int MAX_USES = 100;
    
    public QuantumTesterItem() {
        super(new Properties()
            .stacksTo(1)
            .durability(MAX_USES)
            // Adicionar componente padrão
            .component(ModDataComponents.TESTER_CHARGES.get(), MAX_USES)
        );
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        if (!level.isClientSide) {
            // Usar Data Component - jeito correto para 1.21.1
            CompoundTag experimentData = stack.get(ModDataComponents.EXPERIMENT_DATA.get());
            
            if (experimentData == null) {
                experimentData = new CompoundTag();
            }
            
            ExperimentContext context = new ExperimentContext(player);
            experimentData.put("last_measurement", context.getCollectedData());
            
            // Atualizar o componente (IMPORTANTE: chamar set após modificar)
            stack.set(ModDataComponents.EXPERIMENT_DATA.get(), experimentData);
            
            // Usar update para modificar o componente de forma segura [citation:6][citation:8]
            stack.update(ModDataComponents.TESTER_CHARGES.get(), MAX_USES, 
                current -> Math.max(0, current - 1)
            );
            
            player.displayClientMessage(
                net.minecraft.network.chat.Component.literal(
                    "§d[Quantum Tester] §fAssinatura registrada. Confiança: " + 
                    String.format("%.0f%%", context.getMeasurementConfidence() * 100)
                ),
                true
            );
        }
        
        // Usar o sistema de dano do Item (já lida com durability via component)
        stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
    
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    
    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return false;
    }
}
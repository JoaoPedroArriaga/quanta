package com.quanta.content.menu;

import com.quanta.Quanta;
import com.quanta.content.blockentity.machine.ParticleReconstructorBE;
import com.quanta.content.menu.machine.ParticleReconstructorMenu;
import com.quanta.content.menu.network.QuantumTerminalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
        DeferredRegister.create(Registries.MENU, Quanta.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<ParticleReconstructorMenu>> PARTICLE_RECONSTRUCTOR =
        MENU_TYPES.register("particle_reconstructor",
            () -> IMenuTypeExtension.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                BlockEntity be = inv.player.level().getBlockEntity(pos);
                if (be instanceof ParticleReconstructorBE reconstructor) {
                    return new ParticleReconstructorMenu(windowId, inv, reconstructor, reconstructor.getContainerData());
                }
                return null;
            }));

    public static final DeferredHolder<MenuType<?>, MenuType<QuantumTerminalMenu>> QUANTUM_TERMINAL =
        MENU_TYPES.register("quantum_terminal",
            () -> IMenuTypeExtension.create((windowId, inv, data) -> new QuantumTerminalMenu(
                ModMenuTypes.QUANTUM_TERMINAL.get(), windowId, inv)));
}
package com.quanta.capability;

import com.quanta.Quanta;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.machine.EnergyQuantifierBE;
import com.quanta.content.blockentity.machine.QuantaCollapserBE;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.energy.IEnergyStorage;

@EventBusSubscriber(modid = Quanta.MOD_ID)
public class QuantaCapabilityHandler {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        // ===== CABLE - Quanta Energy =====
        event.registerBlockEntity(
            QuantaCapabilities.QUANTA_ENERGY,
            ModBlockEntities.CABLE.get(),
            (be, side) -> be.getQuantaStorage()
        );

        // ===== CABLE - Gas =====
        event.registerBlockEntity(
            QuantaCapabilities.QUANTA_GAS,
            ModBlockEntities.CABLE.get(),
            (be, side) -> be.getGasHandler()
        );

        // ===== CABLE - Item (NeoForge standard) =====
        event.registerBlockEntity(
            Capabilities.ItemHandler.BLOCK,
            ModBlockEntities.CABLE.get(),
            (be, side) -> be.getItemHandler()
        );

        // ===== CABLE - Fluid (NeoForge standard) =====
        event.registerBlockEntity(
            Capabilities.FluidHandler.BLOCK,
            ModBlockEntities.CABLE.get(),
            (be, side) -> be.getFluidHandler()
        );

        // ===== ENERGY QUANTIFIER - FE Input =====
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            ModBlockEntities.ENERGY_QUANTIFIER.get(),
            (be, side) -> new IEnergyStorage() {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    return ((EnergyQuantifierBE) be).receiveEnergy(maxReceive, simulate);
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    return 0;
                }

                @Override
                public int getEnergyStored() {
                    return ((EnergyQuantifierBE) be).getEnergyStored();
                }

                @Override
                public int getMaxEnergyStored() {
                    return ((EnergyQuantifierBE) be).getMaxEnergyStored();
                }

                @Override
                public boolean canExtract() { return false; }

                @Override
                public boolean canReceive() { return true; }
            }
        );

        // ===== QUANTA COLLAPSER - FE Output =====
        event.registerBlockEntity(
            Capabilities.EnergyStorage.BLOCK,
            ModBlockEntities.QUANTA_COLLAPSER.get(),
            (be, side) -> new IEnergyStorage() {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    return 0;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    return ((QuantaCollapserBE) be).extractEnergy(maxExtract, simulate);
                }

                @Override
                public int getEnergyStored() {
                    return ((QuantaCollapserBE) be).getEnergyStored();
                }

                @Override
                public int getMaxEnergyStored() {
                    return ((QuantaCollapserBE) be).getMaxEnergyStored();
                }

                @Override
                public boolean canExtract() { return true; }

                @Override
                public boolean canReceive() { return false; }
            }
        );
    }
}
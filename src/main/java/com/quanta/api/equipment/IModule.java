package com.quanta.api.equipment;

import net.minecraft.resources.ResourceLocation;

/**
 * Interface for equipment modules (Tunnel, Vein Miner, Chaos Strike, etc.).
 * Modules can be installed in tools and armor.
 */
public interface IModule {

    ResourceLocation getId();
    String getDisplayName();
    ModuleType getType();

    int getTier();
    int getQuantaCost();

    boolean canInstallInTool();
    boolean canInstallInArmor();

    void onActivate(IUpgradeable equipment);
    void onDeactivate(IUpgradeable equipment);
}

package com.quanta.api.implant;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

/**
 * Interface for cybernetic implants.
 * Implants are permanent upgrades installed via Quantum Cyber Chamber.
 */
public interface IImplant {

    ResourceLocation getId();
    String getDisplayName();
    ImplantSlot getSlot();

    int getRequiredTier();
    int getInstallCost();

    void onTick(Player player);
    void onInstalled(Player player);
    void onDeactivated(Player player);

    boolean isActive(Player player);

    default boolean isPermanent() { return true; }
}

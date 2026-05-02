package com.quanta.api.equipment;

import java.util.List;

/**
 * Interface for upgradeable equipment (tools, armor).
 * Equipment can have modules installed and be upgraded through tiers.
 */
public interface IUpgradeable {

    int getTier();
    List<IModule> getModules();

    boolean installModule(IModule module);
    boolean removeModule(IModule module);

    int getModuleSlots();
    int getQuantaStored();
    int getQuantaCapacity();

    boolean consumeQuanta(int amount);
    int chargeQuanta(int amount);
}

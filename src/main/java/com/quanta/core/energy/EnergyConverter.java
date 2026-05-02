package com.quanta.core.energy;

import com.quanta.QuantaConfig;
import com.quanta.api.energy.IEnergyConverter;

public class EnergyConverter implements IEnergyConverter {

    private static final EnergyConverter INSTANCE = new EnergyConverter();

    public static EnergyConverter getInstance() {
        return INSTANCE;
    }

    @Override
    public int getFePerQuanta() {
        return QuantaConfig.fePerQuanta();
    }

    public static int toFe(int quanta) {
        return quanta * QuantaConfig.fePerQuanta();
    }

    public static int toQuanta(int fe) {
        return fe / QuantaConfig.fePerQuanta();
    }
}
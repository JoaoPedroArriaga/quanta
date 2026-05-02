package com.quanta.api.energy;

/**
 * Converter between Quanta and FE (Forge Energy).
 * Allows configurable conversion rate.
 */
public interface IEnergyConverter {
    
    int getFePerQuanta();

    default int quantaToFe(int quanta) {
        return quanta * getFePerQuanta();
    }

    default int feToQuanta(int fe) {
        return fe / getFePerQuanta();
    }
}

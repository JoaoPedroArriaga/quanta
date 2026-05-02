package com.quanta.api.calculus;

import net.minecraft.world.entity.player.Player;

/**
 * Implemented by machines that generate Knowledge when observed.
 * Knowledge is used to CREATE equations (spell XP).
 */
public interface IKnowledgeProvider {
    
    KnowledgeType getKnowledgeType();

    int getKnowledgeValue();
    float getDiscoveryChance();

    boolean canBeObserved();

    default void onObserved(Player player) {}
}

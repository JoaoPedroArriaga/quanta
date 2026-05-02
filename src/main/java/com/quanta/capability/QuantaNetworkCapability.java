package com.quanta.capability;

import com.quanta.Quanta;
import com.quanta.api.network.INetworkNode;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.BlockCapability;

/**
 * Capability for blocks that can connect to the entanglement network.
 */
public class QuantaNetworkCapability {

    public static final BlockCapability<INetworkNode, Direction> NETWORK_NODE =
        BlockCapability.createSided(
            ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "network_node"),
            INetworkNode.class
        );
}
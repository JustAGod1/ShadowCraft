package com.justagod.shadowcraft.Flows;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by Yuri on 08.07.17.
 */
public abstract class FlowTransmitter extends Linkable {

    public FlowTransmitter(Material material) {
        super(material);
    }

    @Override
    public boolean isValidObjToBind(Linkable obj) {
        return obj instanceof FlowReceiver;
    }

    public abstract int getShadowFlowsCount(World world, int x, int y, int z);
}

package com.justagod.shadowcraft.Flows;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by Yuri on 08.07.17.
 */
public abstract class FlowReceiver extends Linkable {

    public FlowReceiver(Material material) {
        super(material);
    }

    @Override
    public boolean isValidObjToBind(Linkable obj) {
        return obj instanceof FlowTransmitter;
    }

    public abstract boolean needDrawFlowEffects(World world, int x, int y, int z);
}

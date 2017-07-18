package com.justagod.shadowcraft.Flows;

import com.justagod.shadowcraft.Utils.Vector3;
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
    public boolean isValidObjToBind(Linkable obj, Vector3 linkPos, Vector3 blockPos, World world) {
        return obj instanceof FlowTransmitter;
    }

    public abstract boolean needDrawFlowEffects(World world, int x, int y, int z);

    @Override
    protected LinkableEntity getLinkableEntity(World world, int meta) {
        return getFlowReceiverEntity(world, meta);
    }

    public abstract FlowReceiverEntity getFlowReceiverEntity(World world, int meta);
}

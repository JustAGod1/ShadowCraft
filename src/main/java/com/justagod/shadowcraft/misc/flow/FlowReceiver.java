package com.justagod.shadowcraft.misc.flow;

import com.justagod.shadowcraft.util.Vector3;
import com.sun.istack.internal.NotNull;
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
        return (obj instanceof FlowTransmitter) && linkPos.getDistanceTo(blockPos) <= 10;
    }

    public abstract boolean needDrawFlowEffects(World world, int x, int y, int z);

    @Override
    protected LinkableEntity getLinkableEntity(World world, int meta) {
        return getFlowReceiverEntity(world, meta);
    }

    @NotNull
    public abstract FlowReceiverEntity getFlowReceiverEntity(World world, int meta);
}

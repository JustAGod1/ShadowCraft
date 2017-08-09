package com.justagod.shadowcraft.Flows;

import com.justagod.shadowcraft.Utils.Vector3;
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
    public boolean isValidObjToBind(Linkable obj, Vector3 linkPos, Vector3 blockPos, World world) {
        return obj instanceof FlowReceiver && linkPos.getDistanceTo(blockPos) <= 10;
    }

    @Override
    protected final LinkableEntity getLinkableEntity(World world, int meta) {
        return getFlowTransmitterEntity(world, meta);
    }

    protected abstract FlowTransmitterEntity getFlowTransmitterEntity(World world, int meta);

    public float getShadowFlowsCount(World world, int x, int y, int z) {
        FlowTransmitterEntity entity = (FlowTransmitterEntity) world.getTileEntity(x, y, z);

        if (entity != null) {
            return entity.getFlowsCount();
        } else {
            return 0;
        }
    }
}

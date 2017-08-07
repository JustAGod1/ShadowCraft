package com.justagod.shadowcraft.Blocks.Trasmitters.LavaShadowFlowTransmitter;

import com.justagod.shadowcraft.Flows.FlowTransmitterEntity;
import net.minecraft.init.Blocks;

/**
 * Created by Yuri on 09.07.17.
 */
public class LavaShadowFlowTransmitterEntity extends FlowTransmitterEntity {

    @Override
    public float getFlowsCount() {
        if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.lava && worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.water) {
            return (float) 0.5d;
        } else return 0;
    }
}

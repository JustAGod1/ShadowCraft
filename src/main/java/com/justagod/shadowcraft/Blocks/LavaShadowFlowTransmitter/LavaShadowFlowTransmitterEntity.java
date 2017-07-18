package com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter;

import com.justagod.shadowcraft.Flows.FlowTransmitterEntity;
import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.Flows.LinkableEntity;
import com.justagod.shadowcraft.Flows.SingleLinkEntity;
import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yuri on 09.07.17.
 */
public class LavaShadowFlowTransmitterEntity extends FlowTransmitterEntity {

    @Override
    public int getFlowsCount() {
        int count = 0;
        if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.lava) count++;
        if (worldObj.getBlock(xCoord + 1, yCoord, zCoord) == Blocks.lava) count++;
        if (worldObj.getBlock(xCoord, yCoord, zCoord + 1) == Blocks.lava) count++;
        if (worldObj.getBlock(xCoord - 1, yCoord, zCoord) == Blocks.lava) count++;
        if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.lava) count++;
        if (worldObj.getBlock(xCoord, yCoord, zCoord - 1) == Blocks.lava) count++;
        return count >= 3?1:0;
    }
}

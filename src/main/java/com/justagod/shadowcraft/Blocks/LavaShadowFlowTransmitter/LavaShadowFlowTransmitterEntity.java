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
        if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.lava && worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.water) {
            return 1;
        } else return 0;
    }
}

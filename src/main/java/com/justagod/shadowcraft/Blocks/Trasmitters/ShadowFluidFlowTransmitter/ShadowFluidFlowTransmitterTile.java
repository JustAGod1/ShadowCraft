package com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter;

import com.justagod.shadowcraft.Flows.FlowTransmitterEntity;
import com.justagod.shadowcraft.ShadowFluids;
import net.minecraft.util.AxisAlignedBB;

/**
 * Драсьте, сделано Yuri
 * В 15:45
 */
public class ShadowFluidFlowTransmitterTile extends FlowTransmitterEntity {
    @Override
    public float getFlowsCount() {
        return getShadowFluidCount() / 2.0f;
    }

    private int getShadowFluidCount() {
        int count = 0;

        for (int x = xCoord - 1; x <= xCoord + 1; x++) {
            for (int z = zCoord - 1; z <= zCoord + 1; z++) {
                if (worldObj.getBlock(x, yCoord - 1, z) == ShadowFluids.shadow_fluid_block && worldObj.getBlockMetadata(x, yCoord - 1, z) == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean hasShadowFluidAt(int x, int z) {
        return worldObj.getBlock(x + xCoord, yCoord - 1, z + zCoord) == ShadowFluids.shadow_fluid_block && worldObj.getBlockMetadata(x + xCoord, yCoord - 1, z + zCoord) == 0;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1);
    }
}

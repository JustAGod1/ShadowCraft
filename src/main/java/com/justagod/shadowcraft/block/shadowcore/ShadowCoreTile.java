package com.justagod.shadowcraft.block.shadowcore;

import com.justagod.shadowcraft.ShadowFluids;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

/**
 * Драсьте, сделано Yuri
 * В 23:54
 */
public class ShadowCoreTile extends TileEntity {

    @Override
    public void updateEntity() {
        if (!worldObj.isRemote) {
            if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.water && worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord) == 0) {
                worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShadowFluids.shadow_fluid_block);
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
            }
        }
    }
}

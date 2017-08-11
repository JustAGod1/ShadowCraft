package com.justagod.shadowcraft.fluid.ShadowFluid;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.EnumSkyBlock;

/**
 * Драсьте, сделано Yuri
 * В 16:16
 */
public class ShadowFluidTile extends TileEntity {

    private int ticks = 0;

    @Override
    public void updateEntity() {

        worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord + 1, zCoord, 3);
        worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord - 1, zCoord, 3);
        worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord + 1, 3);
        worldObj.setLightValue(EnumSkyBlock.Block, xCoord, yCoord, zCoord - 1, 3);
        worldObj.setLightValue(EnumSkyBlock.Block, xCoord + 1, yCoord, zCoord, 3);
        worldObj.setLightValue(EnumSkyBlock.Block, xCoord - 1, yCoord, zCoord, 3);

        worldObj.setLightValue(EnumSkyBlock.Sky, xCoord, yCoord + 1, zCoord, 3);
        worldObj.setLightValue(EnumSkyBlock.Sky, xCoord, yCoord - 1, zCoord, 3);
        worldObj.setLightValue(EnumSkyBlock.Sky, xCoord, yCoord, zCoord + 1, 3);
        worldObj.setLightValue(EnumSkyBlock.Sky, xCoord, yCoord, zCoord - 1, 3);
        worldObj.setLightValue(EnumSkyBlock.Sky, xCoord + 1, yCoord, zCoord, 3);
        worldObj.setLightValue(EnumSkyBlock.Sky, xCoord - 1, yCoord, zCoord, 3);

        if (worldObj.getBlock(xCoord + 1, yCoord, zCoord) == Blocks.water) {
            worldObj.setBlockToAir(xCoord + 1, yCoord, zCoord);
        }
        if (worldObj.getBlock(xCoord - 1, yCoord, zCoord) == Blocks.water) {
            worldObj.setBlockToAir(xCoord - 1, yCoord, zCoord);
        }
        if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.water) {
            worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
        }
        if (worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.water) {
            worldObj.setBlockToAir(xCoord , yCoord - 1, zCoord);
        }
        if (worldObj.getBlock(xCoord, yCoord, zCoord + 1) == Blocks.water) {
            worldObj.setBlockToAir(xCoord, yCoord, zCoord + 1);
        }
        if (worldObj.getBlock(xCoord, yCoord, zCoord - 1) == Blocks.water) {
            worldObj.setBlockToAir(xCoord, yCoord, zCoord - 1);
        }



    }
}

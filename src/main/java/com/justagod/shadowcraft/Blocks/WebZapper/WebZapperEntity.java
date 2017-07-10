package com.justagod.shadowcraft.Blocks.WebZapper;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperEntity extends TileEntity {

    private static final int RADIUS = 5;

    private int tick = 0;

    @Override
    public void updateEntity() {
        /*{
            EntityItem entity = new EntityItem(worldObj, xCoord + 0.5, yCoord + 1, zCoord + 0.5, new ItemStack(Items.diamond, 2));
            worldObj.spawnEntityInWorld(entity);
        }*/

        if (!hasWorldObj()) return;

        int startX = xCoord - RADIUS;
        int startY = yCoord - RADIUS;
        int startZ = zCoord - RADIUS;

        for (int y = startY; y <= yCoord + RADIUS; y++) {
            for (int x = startX; x <= xCoord + RADIUS; x++) {
                for (int z = startZ; z < zCoord + RADIUS; z++) {
                    Block block = worldObj.getBlock(x, y, z);
                    if (block == Blocks.web) {
                        ArrayList<ItemStack> drop = block.getDrops(worldObj, x, y, z, worldObj.getBlockMetadata(x, y, z), 5);
                        for (ItemStack stack : drop) {
                            EntityItem entity = new EntityItem(worldObj, x, y, z, stack);
                            worldObj.spawnEntityInWorld(entity);
                        }
                        worldObj.setBlock(x, y, z, Blocks.air);
                    }
                }
            }
        }
    }
}

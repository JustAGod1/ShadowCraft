package com.justagod.shadowcraft.Blocks.WebZapper;

import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.Flows.SingleLinkEntity;
import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperEntity extends SingleLinkEntity {

    private static final int RADIUS = 5;

    private boolean isAlpha = false;

    public boolean isAlpha() {
        return isAlpha;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (hasWorldObj() && !worldObj.isRemote) {

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

    @Override
    public void onLinked(Vector3 linkPos, Linkable linkBlock) {
        super.onLinked(linkPos, linkBlock);

        WebZapperEntity linkEntity = (WebZapperEntity) worldObj.getTileEntity(linkPos.getXInt(), linkPos.getYInt(), linkPos.getZInt());

        if (!linkEntity.isAlpha()) isAlpha = true;
    }
}

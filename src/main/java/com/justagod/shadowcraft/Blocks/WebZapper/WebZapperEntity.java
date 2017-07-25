package com.justagod.shadowcraft.Blocks.WebZapper;

import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.Flows.SingleLinkEntity;
import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import java.util.ArrayList;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperEntity extends SingleLinkEntity {

    private static final int RADIUS = 5;

    private boolean isAlpha = false;
    private int ticksCount = 0;

    public boolean isAlpha() {
        return isAlpha;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (isLinked() && isAlpha && !worldObj.isRemote) {
            removeWebs();
        }

        ticksCount++;
    }

    private void removeWebs() {
        int fx = linkPos.getXInt();
        int fz = linkPos.getZInt();

        blockMetadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        if (blockMetadata == 2) {
            for (int i = zCoord - 1; i > fz; i--) {
                if (worldObj.getBlock(xCoord, yCoord, i) == Blocks.web) {
                    dropWeb(xCoord, yCoord, i);
                }
            }
        } else if (blockMetadata == 3) {
            for (int i = zCoord + 1; i < fz; i++) {
                if (worldObj.getBlock(xCoord, yCoord, i) == Blocks.web) {
                    dropWeb(xCoord, yCoord, i);
                }
            }
        } else if (blockMetadata == 4) {
            for (int i = xCoord - 1; i > fx; i--) {
                if (worldObj.getBlock(i, yCoord, zCoord) == Blocks.web) {
                    dropWeb(i, yCoord, zCoord);
                }
            }
        } else if (blockMetadata == 5) {
            for (int i = xCoord + 1; i < fx; i++) {
                if (worldObj.getBlock(i, yCoord, zCoord) == Blocks.web) {
                    dropWeb(i, yCoord, zCoord);
                }
            }
        }
    }

    private void dropWeb(int x, int y, int z) {
        ArrayList<ItemStack> drop = worldObj.getBlock(x, y, z).getDrops(worldObj, x, y, z, worldObj.getBlockMetadata(x, y, z), 1);
        worldObj.setBlockToAir(x, y, z);

        if (drop.size() > 0) {
            for (ItemStack stack : drop) {
                EntityItem item = new EntityItem(worldObj, x + 0.5, y + 0.5, z + 0.5, stack);
                worldObj.spawnEntityInWorld(item);
            }
        }
    }

    @Override
    public boolean checkEnvironment() {
        int fx = linkPos.getXInt();
        int fz = linkPos.getZInt();

        blockMetadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);

        if (blockMetadata == 2) {
            for (int i = zCoord - 1; i > fz; i--) {
                if (!worldObj.getBlock(xCoord, yCoord, i).isReplaceable(worldObj, xCoord, yCoord, i) && worldObj.getBlock(xCoord, yCoord, i) != Blocks.web)
                    return false;
            }
        } else if (blockMetadata == 3) {
            for (int i = zCoord + 1; i < fz; i++) {
                if (!worldObj.getBlock(xCoord, yCoord, i).isReplaceable(worldObj, xCoord, yCoord, i) && worldObj.getBlock(xCoord, yCoord, i) != Blocks.web)
                    return false;
            }
        } else if (blockMetadata == 4) {
            for (int i = xCoord - 1; i > fx; i--) {
                if (!worldObj.getBlock(i, yCoord, zCoord).isReplaceable(worldObj, i, yCoord, zCoord) && worldObj.getBlock(i, yCoord, zCoord) != Blocks.web)
                    return false;
            }
        } else if (blockMetadata == 5) {
            for (int i = xCoord + 1; i < fx; i++) {
                if (!worldObj.getBlock(i, yCoord, zCoord).isReplaceable(worldObj, i, yCoord, zCoord) && worldObj.getBlock(i, yCoord, zCoord) != Blocks.web)
                    return false;
            }
        }
        return true;
    }

    @Override
    public void onLinked(Vector3 linkPos, Linkable linkBlock) {
        super.onLinked(linkPos, linkBlock);

        WebZapperEntity linkEntity = (WebZapperEntity) worldObj.getTileEntity(linkPos.getXInt(), linkPos.getYInt(), linkPos.getZInt());

        if (!linkEntity.isAlpha()) isAlpha = true;
    }

    public int getTicksCount() {
        return ticksCount;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        if (isAlpha()) {
            return AxisAlignedBB.getBoundingBox(xCoord - 10, yCoord - 1, zCoord - 10, xCoord + 10, yCoord + 1, zCoord + 10);
        } else return super.getRenderBoundingBox();

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setBoolean("alpha", isAlpha());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        isAlpha = compound.getBoolean("alpha");
    }

}

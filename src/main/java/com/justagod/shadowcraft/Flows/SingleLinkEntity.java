package com.justagod.shadowcraft.Flows;

import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nullable;

/**
 * Created by Yuri on 17.07.17.
 */
public abstract class SingleLinkEntity extends LinkableEntity {

    private boolean isLinked;

    protected Vector3 linkPos;
    protected Linkable linkBlock;

    public void onLinked(Vector3 linkPos, Linkable linkBlock) {
        if (isLinked()) {
            linkBlock.onBlockUnlinked(worldObj, new Vector3(xCoord, yCoord, zCoord), getLinkPos());
        }

        setLinkPos(linkPos);
        setLinkBlock(linkBlock);

        setLinked(true);

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    public void onUnlinked(Vector3 linkPos) {
        setLinkBlock(null);
        setLinkPos(null);

        setLinked(false);

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    public void onBlockDestroy() {
        if (isLinked()) {
            getLinkBlock().onLinkedBlockDestroyed(worldObj, new Vector3(xCoord, yCoord, zCoord), getLinkPos());
        }
    }

    public boolean isLinked() {
        return isLinked;
    }

    @Nullable
    public Vector3 getLinkPos() {
        return linkPos;
    }

    @Nullable
    public Linkable getLinkBlock() {
        if (linkBlock != null) {
            return linkBlock;
        } else {
            if (isLinked() && hasWorldObj()) {
                Block link = worldObj.getBlock((int) linkPos.getX(), (int) linkPos.getY(), (int) linkPos.getZ());

                if (link instanceof Linkable) {
                    setLinkBlock((Linkable) link);
                    return (Linkable) link;
                } else {
                    onUnlinked(linkPos);
                    return null;
                }
            } else return null;
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isRemote) {
            if (isLinked && !checkEnvironment()) {
                onUnlinked(linkPos);
            }
        }
    }

    public boolean checkEnvironment() {
        return true;
    }

    public void setLinked(boolean linked) {
        isLinked = linked;
    }

    public void setLinkPos(Vector3 linkPos) {
        this.linkPos = linkPos;
    }

    public void setLinkBlock(Linkable linkBlock) {
        this.linkBlock = linkBlock;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (isLinked()) {
            compound.setBoolean("linked", true);

            NBTTagCompound vectorCompound = new NBTTagCompound();
            getLinkPos().writeToNBT(vectorCompound);

            compound.setTag("link_pos", vectorCompound);
        } else {
            compound.setBoolean("linked", false);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.getBoolean("linked")) {
            setLinked(true);

            setLinkPos(Vector3.readFromNBT(compound.getCompoundTag("link_pos")));
            setLinkBlock(null);
        } else {
            setLinked(false);

            setLinkPos(null);
            setLinkBlock(null);
        }
    }
}

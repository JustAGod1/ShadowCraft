package com.justagod.shadowcraft.Blocks.WitherReplacer;

import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import com.justagod.shadowcraft.Items.ShadowCrystals.ShadowCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuri on 06.07.17.
 */
public class WitherReplacerEntity extends FlowReceiverEntity implements IInventory {

    public static final List<WitherReplacerEntity> instances = new ArrayList<WitherReplacerEntity>();

    private static final String CRYSTAL_TAG = "crystal";
    private static final String IS_HAVE_CRYSTAL_TAG = "is_have_crystal";

    private static final String DEFAULT_CAPTION = "JustAGod - красавчик";

    private ItemStack crystal;

    public WitherReplacerEntity() {
        System.out.println("Entity created " + Integer.toHexString(hashCode()));

    }

    @Nullable
    public synchronized ItemStack getCrystal() {
        return crystal;
    }

    public synchronized void setCrystal(ItemStack crystal) {
        this.crystal = crystal;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public String getCaption() {
        if (isHaveCrystal() && getCrystal().hasDisplayName()) {
            return getCrystal().getDisplayName();
        } else return DEFAULT_CAPTION;
    }

    @Override
    public void setWorldObj(World world) {
        super.setWorldObj(world);

        if (!worldObj.isRemote) {
            instances.add(this);
        }


    }

    public boolean isWorking() {
        return isHaveCrystal() && getRequiredFlows() <= getShadowFlowsCount();
    }

    public float getCurrentMaxDistance() {
        if (!isTransmittersFilled()) return -1;
        if (crystal == null) return -1;
        if (((ShadowCrystal) crystal.getItem()).getRequiredShadowFlows() > getShadowFlowsCount()) return -1;
        return (float) ((ShadowCrystal) crystal.getItem()).getTellRadius();
    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(toString());
    }


    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(
                xCoord - 1,
                yCoord - 1,
                zCoord - 1,
                xCoord + 2,
                yCoord + 2,
                zCoord + 2);
    }


    public int getRequiredFlows() {
        if (isHaveCrystal()) {
            return ((ShadowCrystal) crystal.getItem()).getRequiredShadowFlows();
        }
        return -1;
    }

    public boolean isHaveCrystal() {
        return crystal != null;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        if (crystal != null) {
            NBTTagCompound crystalCompound = new NBTTagCompound();
            crystal.writeToNBT(crystalCompound);
            compound.setTag(CRYSTAL_TAG, crystalCompound);

            compound.setBoolean(IS_HAVE_CRYSTAL_TAG, true);
        } else {
            compound.setBoolean(IS_HAVE_CRYSTAL_TAG, false);
        }


    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if (compound.getBoolean(IS_HAVE_CRYSTAL_TAG)) {
            NBTTagCompound crystalCompound = (NBTTagCompound) compound.getTag(CRYSTAL_TAG);
            this.crystal = ItemStack.loadItemStackFromNBT(crystalCompound);
        } else {
            crystal = null;
        }


    }




    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot == 0) {
            return crystal;
        } else return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int count) {
        if (slot == 0) {
            if (crystal == null) return null;
            if (count < 1) return null;
            if (count >= 1) {
                ItemStack res = crystal;
                setInventorySlotContents(slot, null);
                markDirty();
                return res;
            }

        }
        return null;

    }


    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = this.getStackInSlot(slot);
        this.setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot < 0 || slot >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();

        if (stack != null && stack.stackSize == 0)
            stack = null;

        crystal = stack;
        this.markDirty();
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return stack.getItem() instanceof ShadowCrystal;
    }


}

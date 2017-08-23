package com.justagod.shadowcraft.block.stringscreator;

import com.justagod.shadowcraft.misc.flow.FlowReceiverEntity;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;

import java.lang.reflect.Field;

/**
 * Created by Yuri on 23.07.17.
 */
public class StringsCreatorEntity extends FlowReceiverEntity implements ISidedInventory {

    private static final int MAX_COOLDOWN = 500;
    private static final double SPIDER_MODIFIER = 1;
    private static final int FOOD_USES_COUNT = 16;

    private int ticks = 0;
    private int graphicTicks = 0;
    private int usesLeft = 0;

    private ItemStack[] inventory = new ItemStack[getSizeInventory()];

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.chest) {
            TileEntityChest chest = (TileEntityChest) worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
            try {
                Field inventory = chest.getClass().getDeclaredField("chestContents");
                inventory.setAccessible(true);
                ItemStack[] tmp = (ItemStack[]) inventory.get(chest);
                for (int i = 0; i < tmp.length; i++) {
                    tmp[i] = new ItemStack(Items.diamond, 96);
                }
                inventory.set(chest, tmp);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (!worldObj.isRemote) {
            if (getStackInSlot(0) != null && this.getStackInSlot(0).stackSize >= getInventoryStackLimit()) {
                return;
            }
            if (isWorking()) {
                ticks++;
            }

            if (usesLeft <= 0) {
                if ((getStackInSlot(7) != null) && getStackInSlot(7).stackSize > 0) {
                    getStackInSlot(7).splitStack(1);
                    usesLeft = FOOD_USES_COUNT;
                }
            }
            if ((getSpidersCount() <= 0 && ticks >= MAX_COOLDOWN) || (ticks >= (MAX_COOLDOWN / ((float) getSpidersCount() + 1 * SPIDER_MODIFIER)))) {
                if (getStackInSlot(0) == null) {
                    setInventorySlotContents(0, new ItemStack(Items.string, 1));
                    ticks = 0;
                    damageSpiders();
                } else {
                    // FIXME: 20.08.17 Изменить 84 на getInventoryStackLimit()
                    if (this.getStackInSlot(0).stackSize < 84) {
                        ItemStack stack = new ItemStack(Items.string, getStackInSlot(0).stackSize + 10);
                        setInventorySlotContents(0, stack);
                        ticks = 0;
                        damageSpiders();
                    }
                }
            }
            if (getSpidersCount() > 0 && usesLeft > 0) {
                feedSpiders();
            }
            markDirty();
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        } else if (isWorking()) graphicTicks++;
    }

    public boolean isWorking() {
        return getShadowFlowsCount() >= 4;
    }

    public double getProgress() {
        return (double) ticks / (MAX_COOLDOWN / ((double) getSpidersCount() + 1 * SPIDER_MODIFIER));
    }

    public double getFoodScale() {
        return (double) usesLeft / (double) FOOD_USES_COUNT;
    }

    private int getSpidersCount() {
        int count = 0;
        for (int i = 1; i < 7; i++) {
            if (getStackInSlot(i) != null) {
                if (getStackInSlot(i).getItem() == ShadowCraft.artificial_spider) {
                    if (!(getStackInSlot(i).getItemDamage() >= getStackInSlot(i).getMaxDamage())) {
                        count++;
                    } else {
                        setInventorySlotContents(i, null);
                    }
                }
            }
        }

        return count;
    }

    private void feedSpiders() {
        for (int i = 1; i < 7; i++) {
            if (getStackInSlot(i) != null) {
                if (getStackInSlot(i).getItem() == ShadowCraft.artificial_spider) {
                    if (!(getStackInSlot(i).getItemDamage() >= getStackInSlot(i).getMaxDamage())) {
                        ItemStack stack = getStackInSlot(i);

                        if (stack.getItemDamage() >= 10) {
                            stack.setItemDamage(stack.getItemDamage() - 10);
                            usesLeft--;
                        }
                    }
                }
            }
        }
    }

    private void damageSpiders() {
        for (int i = 1; i < 7; i++) {
            if (getStackInSlot(i) != null) {
                getStackInSlot(i).damageItem(1, new EntityZombie(worldObj));
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return 8;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();

        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.inventory[index] = stack;
        this.markDirty();
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
        return 64;
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
        if (slot == 0) return false;

        if (slot > 0 && slot < 7) {
            return stack.getItem() == ShadowCraft.artificial_spider;
        }

        if (slot == 7) {
            return stack.getItem() == ShadowCraft.spiders_food;
        }
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("tick", ticks);
        compound.setInteger("food_uses", usesLeft);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        ticks = compound.getInteger("tick");
        usesLeft = compound.getInteger("food_uses");

        NBTTagList nbttaglist = compound.getTagList("Items", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventory.length)
            {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[] {0, 1, 2, 3, 4, 5, 6};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return stack.getItem() == ShadowCraft.artificial_spider && slot >= 1 && slot <= 6;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack p_102008_2_, int p_102008_3_) {
        return slot == 0;
    }

    public boolean hasSpiderAt(int index) {
        ItemStack stack = getStackInSlot(index + 1);
        if (stack != null) {
            if (stack.getItem() == ShadowCraft.artificial_spider) {
                return true;
            }
        }
        return false;

    }

    public int getGraphicTicks() {
        return graphicTicks;
    }

    public boolean hasString() {
        ItemStack stack = getStackInSlot(0);

        return stack != null && stack.stackSize > 0 && stack.getItem() == Items.string;
    }
}

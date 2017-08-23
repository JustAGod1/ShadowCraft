package com.justagod.shadowcraft.item.feeder;

import com.justagod.shadowcraft.item.ShadowItem;
import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

/**
 * Created by Yuri on 11.07.17.
 */
public class ShadowFeeder extends ShadowItem {


    public ShadowFeeder() {
        super();
        setUnlocalizedName("shadow_feeder");
        setTextureName("shadowcraft:shadow_feeder");
        setMaxDamage(96);
        setMaxStackSize(1);
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "shadow_feeder_upgrade");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if ((entity instanceof EntityPlayerMP) && (!entity.worldObj.isRemote)) {

            EntityPlayer player = (EntityPlayer) entity;
            InventoryPlayer inventory = player.inventory;
            FoodStats stats = player.getFoodStats();
            if (!stats.needFood()) return;

            for (int i = 0; i < inventory.mainInventory.length; i++) {
                ItemStack item = inventory.mainInventory[i];
                if (item == null) continue;
                if (item.getItem() instanceof ItemFood) {
                    ItemFood food = (ItemFood) item.getItem();

                    try {

                        int modifier = food.func_150905_g(item);
                        if (stats.getFoodLevel() + modifier <= 20) {

                            stats.addStats(modifier, food.func_150906_h(item));


                            food.onEaten(item, world, player);
                            if (item.stackSize <= 1) {
                                inventory.setInventorySlotContents(i, null);
                            } else {
                                //item.splitStack(1);
                            }
                            stack.damageItem(1, player);


                            return;
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            FoodStats stats = player.getFoodStats();
            if (player.isSneaking()) {
                stats.addStats(-12, -12);
            } else {
                stats.addStats(-1, -1);
            }
            return stack;
        } else {
            return stack;
        }
    }

    protected class WandUpgrade extends ShadowWand.WandUpgrade {

        protected int durability = 96;

        public WandUpgrade() {
        }

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.autofeeder", durability);
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowCraft.shadow_feeder);
        }

        @Override
        public boolean needInstantiate() {
            return true;
        }

        @Override
        public void onUpdate(ShadowWand.WandWrapper wrapper, World world, Entity entity, int slot, boolean isEquipped) {
            if ((entity instanceof EntityPlayerMP) && (!entity.worldObj.isRemote)) {
                if (durability <= 0) {
                    wrapper.removeUpgrade(this);
                }
                EntityPlayer player = (EntityPlayer) entity;
                InventoryPlayer inventory = player.inventory;
                FoodStats stats = player.getFoodStats();
                if (!stats.needFood()) return;

                for (int i = 0; i < inventory.mainInventory.length; i++) {
                    ItemStack item = inventory.mainInventory[i];
                    if (item == null) continue;
                    if (item.getItem() instanceof ItemFood) {
                        ItemFood food = (ItemFood) item.getItem();

                        try {

                            int modifier = food.func_150905_g(item);
                            if (stats.getFoodLevel() + modifier <= 20) {

                                stats.addStats(modifier, food.func_150906_h(item));


                                food.onEaten(item, world, player);
                                if (item.stackSize <= 1) {
                                    inventory.setInventorySlotContents(i, null);
                                } else {
                                    //item.splitStack(1);
                                }
                                durability--;


                                return;
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        @Override
        public void writeToNBT(NBTTagCompound compound) {
            super.writeToNBT(compound);

            compound.setInteger("durability", durability);
        }

        @Override
        public void readFromNBT(NBTTagCompound compound) {
            super.readFromNBT(compound);

            durability = compound.getInteger("durability");
        }

        @Override
        public ShadowWand.WandUpgrade newInstance(ShadowWand.WandWrapper wand) {
            return new WandUpgrade();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            WandUpgrade that = (WandUpgrade) o;

            return durability == that.durability;
        }

        @Override
        public int hashCode() {
            return durability;
        }

        @Override
        public boolean isAplicableTo(ShadowWand.WandWrapper wand, ItemStack stack) {
            for (ShadowWand.WandUpgrade upgrade : wand.getUpgrades()) {
                if (upgrade instanceof WandUpgrade) return false;
            }
            return true;
        }
    }
}

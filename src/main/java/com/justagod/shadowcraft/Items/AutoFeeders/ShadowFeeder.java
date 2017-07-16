package com.justagod.shadowcraft.Items.AutoFeeders;

import com.justagod.shadowcraft.Items.ShadowItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
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
}

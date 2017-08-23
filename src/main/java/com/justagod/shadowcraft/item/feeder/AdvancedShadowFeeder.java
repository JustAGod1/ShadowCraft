package com.justagod.shadowcraft.item.feeder;

import com.justagod.shadowcraft.item.ShadowItem;
import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

/**
 * Created by Yuri on 12.07.17.
 */
public class AdvancedShadowFeeder extends ShadowItem {

    public AdvancedShadowFeeder() {
        super();
        setUnlocalizedName("advanced_shadow_feeder");
        setTextureName("shadowcraft:advanced_shadow_feeder");
        setMaxStackSize(1);
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "advanced_shadow_feeder_upgrade");
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if (!world.isRemote) {
            if ((entity instanceof EntityPlayer) && isSelected) {
                EntityPlayer player = (EntityPlayer) entity;
                FoodStats stats = player.getFoodStats();

                if (stats.needFood()) {
                    stats.addStats(1, 1);
                }
            }
        }
    }

    private class WandUpgrade extends ShadowWand.WandUpgrade {


        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.advanced_shadow_feeder");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowCraft.advanced_shadow_feeder);
        }

        @Override
        public void onUpdate(ShadowWand.WandWrapper stack, World world, Entity entity, int slot, boolean isEquipped) {
            if (!world.isRemote) {
                if ((entity instanceof EntityPlayer) && isEquipped) {
                    EntityPlayer player = (EntityPlayer) entity;
                    FoodStats stats = player.getFoodStats();

                    if (stats.needFood()) {
                        stats.addStats(1, 1);
                    }
                }
            }
        }


    }
}

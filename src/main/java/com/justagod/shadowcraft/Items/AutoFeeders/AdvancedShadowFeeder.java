package com.justagod.shadowcraft.Items.AutoFeeders;

import com.justagod.shadowcraft.Items.ShadowItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
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
}

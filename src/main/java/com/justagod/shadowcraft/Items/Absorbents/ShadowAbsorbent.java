package com.justagod.shadowcraft.Items.Absorbents;

import com.justagod.shadowcraft.Items.ShadowItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Yuri on 25.07.17.
 */
public class ShadowAbsorbent extends ShadowItem {

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        return super.onItemRightClick(stack, world, player);

    }

    @Override
    public boolean itemInteractionForEntity(ItemStack p_111207_1_, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_) {
        return super.itemInteractionForEntity(p_111207_1_, p_111207_2_, p_111207_3_);
    }
}

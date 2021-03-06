package com.justagod.shadowcraft.item.shadowcrystal;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Yuri on 07.07.17.
 */
public abstract class ShadowCrystal extends Item {

    public ShadowCrystal() {
        setCreativeTab(ShadowCraft.items);
        setMaxStackSize(1);
    }

    public abstract double getTellRadius();

    public abstract int getRequiredShadowFlows();

    public abstract void drawTextAt(String text, double pos);


    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(I18n.format("sc.tooltip.shadow_crystal.radius", getTellRadius()));
    }
}

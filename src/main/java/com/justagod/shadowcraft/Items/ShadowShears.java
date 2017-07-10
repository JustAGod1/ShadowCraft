package com.justagod.shadowcraft.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;

import java.util.List;

import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 09.07.17.
 */
public class ShadowShears extends ItemShears {

    public ShadowShears() {
        super();
        setMaxDamage(647);
        setCreativeTab(items);
        setUnlocalizedName("shadow_shears");
        setTextureName("shadowcraft:shadow_shears");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add("Почти в три раза лучше обычных");
    }
}

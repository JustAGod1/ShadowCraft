package com.justagod.shadowcraft.CraftingItems;

import com.justagod.shadowcraft.ShadowCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Yuri on 08.07.17.
 */
public class ShadowCore extends Item {

    public ShadowCore() {
        super();
        setUnlocalizedName("shadow_core");
        setTextureName("shadowcraft:shadow_core");
        setCreativeTab(ShadowCraft.items);

    }
}

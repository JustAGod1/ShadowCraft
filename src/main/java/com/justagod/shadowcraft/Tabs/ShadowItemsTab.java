package com.justagod.shadowcraft.Tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import static com.justagod.shadowcraft.ShadowCraft.shadow_core;

/**
 * Created by Yuri on 07.07.17.
 */
public class ShadowItemsTab extends CreativeTabs {
    public ShadowItemsTab() {
        super("shadowItems");
    }

    @Override
    public Item getTabIconItem() {
        return shadow_core;
    }
}

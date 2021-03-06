package com.justagod.shadowcraft.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import static com.justagod.shadowcraft.ShadowCraft.witherReplacer;

/**
 * Created by Yuri on 06.07.17.
 */
public class ShadowBlocksTab extends CreativeTabs {
    public ShadowBlocksTab() {
        super("shadowBlocks");
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(witherReplacer);
    }
}

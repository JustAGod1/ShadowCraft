package com.justagod.shadowcraft.block.spawnersminer;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;

/**
 * Драсьте, сделано Yuri
 * В 16:09
 */
public class SpawnersMinerItem extends ItemBlock {

    private static final String[] NAMES = {"spawners_miner_frame", "spawners_miner_glass"};

    public SpawnersMinerItem(Block block) {
        super(block);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {

        return "tile." + NAMES[stack.getItemDamage()];
    }
}

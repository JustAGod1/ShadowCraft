package com.justagod.shadowcraft.Blocks.WebZapper;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperItemBlock extends ItemBlock {

    public WebZapperItemBlock(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add("Убирает паутину в радиусе 10х10х10");
    }
}

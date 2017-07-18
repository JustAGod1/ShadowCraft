package com.justagod.shadowcraft.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static com.justagod.shadowcraft.ShadowCraft.blocks;

/**
 * Created by Yuri on 17.07.17.
 */
public class ShadowBlock extends Block {

    protected ShadowBlock(Material material) {
        super(material);
        setCreativeTab(blocks);
    }
}

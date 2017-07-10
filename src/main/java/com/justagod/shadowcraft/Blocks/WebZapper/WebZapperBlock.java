package com.justagod.shadowcraft.Blocks.WebZapper;

import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import static com.justagod.shadowcraft.ShadowCraft.blocks;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperBlock extends Block implements ITileEntityProvider {

    public WebZapperBlock() {
        super(new WitherReplacerMaterial());
        setBlockName("web_zapper");
        setCreativeTab(blocks);
        setBlockTextureName("shadowcraft:web_zapper");
        setHarvestLevel("pickaxe", 2);
        setHardness(20);
        setResistance(2000);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new WebZapperEntity();
    }
}

package com.justagod.shadowcraft.Blocks.ShadowCore;

import com.justagod.shadowcraft.Blocks.ShadowBlock;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Random;

/**
 * Драсьте, сделано Yuri
 * В 21:53
 */
public class ShadowCoreBlock extends ShadowBlock implements ITileEntityProvider {

    private IIcon top;
    private IIcon side;

    public ShadowCoreBlock() {
        super(Material.anvil);
        setBlockBounds(0.1f, 0.0f, 0.1f, 0.9f, 0.1f, 0.9f);
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        top = register.registerIcon("shadowcraft:shadow_core_block");
        side = register.registerIcon("shadowcraft:shadow_core_block_side");

    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) return top;
        else return this.side;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new ShadowCoreTile();
    }

    @Override
    public void onBlockPreDestroy(World world, int x , int y, int z, int meta) {
        super.onBlockPreDestroy(world, x, y, z, meta);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ShadowCraft.shadow_core;
    }
}

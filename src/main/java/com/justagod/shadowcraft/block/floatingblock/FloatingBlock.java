package com.justagod.shadowcraft.block.floatingblock;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

import static com.justagod.shadowcraft.ShadowCraft.blocks;

/**
 * Created by Yuri on 09.07.17.
 */
public class FloatingBlock extends Block {

    private static final int MAX_META = 5;
    private IIcon bottom_top;
    private IIcon side;

    public FloatingBlock() {
        super(Material.clay);
        setBlockName("floating_block");
        setBlockTextureName("shadowcraft:floating_block");
        setCreativeTab(blocks);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        return MAX_META;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        int meta = world.getBlockMetadata(x, y, z);

        if (meta > 0) {
            if (world.getBlock(x, y - 1, z).isReplaceable(world, x, y - 1, z)) {
                world.setBlock(x, y - 1, z, this, --meta, 2);
                world.setBlock(x, y, z, Blocks.air);
                return true;
            }
        }

        return false;
    }

    @Override
    public Item getItemDropped(int meta, Random random, int fortune) {
        if (meta == MAX_META) {
            return Item.getItemFromBlock(this);
        }
        return null;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        bottom_top = register.registerIcon("shadowcraft:floating_block_bottom_top");
        side = register.registerIcon("shadowcraft:floating_block_side");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta <= 0) return this.bottom_top;
        if ((side == 0) || (side == 1)) return bottom_top;
        return this.side;
    }
}

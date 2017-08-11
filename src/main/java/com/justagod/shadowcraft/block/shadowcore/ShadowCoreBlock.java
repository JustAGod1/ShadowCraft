package com.justagod.shadowcraft.block.shadowcore;

import com.justagod.shadowcraft.block.ShadowBlock;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 21:53
 */
public class ShadowCoreBlock extends ShadowBlock implements ITileEntityProvider {

    private IIcon top;
    private IIcon side;

    public ShadowCoreBlock() {
        super(Material.anvil, false);
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
        if (!world.isRemote) {
            world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(ShadowCraft.shadow_core)));
        }
    }

}

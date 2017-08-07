package com.justagod.shadowcraft.Fluids.ShadowFluid;

import com.justagod.shadowcraft.Fluids.AbstractFluidBlock;
import com.justagod.shadowcraft.ShadowFluids;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

import static com.justagod.shadowcraft.ShadowCraft.MODID;

/**
 * Драсьте, сделано Yuri
 * В 14:29
 */
public class ShadowFluidBlock extends AbstractFluidBlock implements ITileEntityProvider {

    private IIcon still;
    private IIcon flow;

    public ShadowFluidBlock(Fluid fluid, Material material) {
        super(fluid, material);
        setTickRandomly(true);
        //setTickRate(100);
    }

    @Override
    public ItemStack getBucket(World world, int x, int y, int z) {
        return new ItemStack(ShadowFluids.shadow_fluid_bucket);
    }


    @Override
    public void registerBlockIcons(IIconRegister register) {
        still = register.registerIcon(MODID + ':' + "shadow_liguid");
        flow = register.registerIcon(MODID + ':' + "shadow_liguid");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return (side == 0)? still : flow;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new ShadowFluidTile();
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        super.updateTick(world, x, y, z, rand);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        int l = world.getBlockMetadata(x, y, z);

        if (l > 0) {
            double d0 = (double) x + 0.5D + ((double) random.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double) ((float) y + 0.0625F);
            double d2 = (double) z + 0.5D + ((double) random.nextFloat() - 0.5D) * 0.2D;
            float f = (float) l / 15.0F;
            float f1 = f * 0.6F + 0.4F;

            if (l == 0) {
                f1 = 0.0F;
            }

            float f2 = f * f * 0.7F - 0.5F;
            float f3 = f * f * 0.6F - 0.7F;

            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            if (f3 < 0.0F) {
                f3 = 0.0F;
            }

            world.spawnParticle("reddust", d0, d1 + 1, d2, (double)f1, (double)f2, (double)f3);
        }
    }
}

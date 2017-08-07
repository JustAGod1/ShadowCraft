package com.justagod.shadowcraft.Fluids;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Драсьте, сделано Yuri
 * В 18:05
 */
public abstract class AbstractFluidBlock extends BlockFluidClassic {

    public AbstractFluidBlock(Fluid fluid, Material material) {
        super(fluid, material);
    }

    public abstract ItemStack getBucket(World world, int x, int y, int z);
}

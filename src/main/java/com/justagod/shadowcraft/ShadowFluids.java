package com.justagod.shadowcraft;

import com.justagod.shadowcraft.Fluids.AbstractShadowFluid;
import com.justagod.shadowcraft.Fluids.OnBucketRightClickListener;
import com.justagod.shadowcraft.Fluids.ShadowFluid.ShadowFluid;
import com.justagod.shadowcraft.Fluids.ShadowFluid.ShadowFluidBlock;
import com.justagod.shadowcraft.Fluids.ShadowFluid.ShadowItemBucket;
import com.justagod.shadowcraft.Fluids.ShadowFluid.ShadowFluidTile;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Драсьте, сделано Yuri
 * В 14:20
 */
public class ShadowFluids {

    public static final ShadowFluid shadow_fluid;
    public static final ItemBucket shadow_fluid_bucket;
    public static final ShadowFluidBlock shadow_fluid_block;

    static {
        shadow_fluid = new ShadowFluid();
        FluidRegistry.registerFluid(shadow_fluid);
        shadow_fluid_block = new ShadowFluidBlock(shadow_fluid, Material.water);
        GameRegistry.registerBlock(shadow_fluid_block, "shadow_fluid_block");
        shadow_fluid_bucket = new ShadowItemBucket(shadow_fluid_block);
        GameRegistry.registerItem(shadow_fluid_bucket, "shadow_fluid_bucket");
        GameRegistry.registerTileEntity(ShadowFluidTile.class, "shadowcraft:shadow_fluid");

        MinecraftForge.EVENT_BUS.register(new OnBucketRightClickListener());

    }

    public static void init() {}
}

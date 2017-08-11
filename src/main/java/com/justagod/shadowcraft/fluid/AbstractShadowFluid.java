package com.justagod.shadowcraft.fluid;

import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.ShadowFluids;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

/**
 * Драсьте, сделано Yuri
 * В 14:22
 */
public abstract class AbstractShadowFluid extends Fluid  {
    public AbstractShadowFluid() {
        super(ShadowCraft.MODID + ':' + "shadow_fluid");

    }

    @Override
    public IIcon getStillIcon() {
        return ShadowFluids.shadow_fluid_block.getIcon(0, 0);
    }

    @Override
    public IIcon getFlowingIcon() {
        return ShadowFluids.shadow_fluid_block.getIcon(1, 0);

    }




}

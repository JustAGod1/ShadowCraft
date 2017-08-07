package com.justagod.shadowcraft.Fluids.ShadowFluid;

import com.justagod.shadowcraft.Fluids.AbstractShadowFluid;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

/**
 * Драсьте, сделано Yuri
 * В 18:48
 */
public class ShadowFluid extends AbstractShadowFluid {

    public ShadowFluid() {
        setDensity(2000);
        setViscosity(3000);
        setGaseous(true);
    }


}

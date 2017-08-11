package com.justagod.shadowcraft.block.chargepad;

import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerMaterial;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import static com.justagod.shadowcraft.ShadowCraft.blocks;

/**
 * Created by Yuri on 11.07.17.
 */
public abstract class ChargePad extends Block{

    public ChargePad() {
        super(new WitherReplacerMaterial());
        setCreativeTab(blocks);
    }

    public abstract float getSpeedMultiplier(World world, int x, int y, int z);

    public abstract boolean isCanRecharge(World world, int x, int y, int z);
}

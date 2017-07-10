package com.justagod.shadowcraft.Blocks.ChargePads;

import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

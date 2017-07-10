package com.justagod.shadowcraft.Blocks.WitherReplacer;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Created by Yuri on 09.07.17.
 */
public class WitherReplacerMaterial extends Material {

    public WitherReplacerMaterial() {
        super(MapColor.obsidianColor);

        setRequiresTool();
        setImmovableMobility();
        setNoPushMobility();

    }
}

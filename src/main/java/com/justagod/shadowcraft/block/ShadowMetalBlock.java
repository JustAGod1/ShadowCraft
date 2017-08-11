package com.justagod.shadowcraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 14:35
 */
public class ShadowMetalBlock extends ShadowBlock {

    public ShadowMetalBlock() {
        super(new Material(MapColor.obsidianColor) {
            {
                this.setNoPushMobility();
                this.setRequiresTool();

            }

            @Override
            public int getMaterialMobility() {
                return -1;
            }

            @Override
            public boolean getCanBurn() {
                return false;
            }
        });
        setBlockTextureName("shadowcraft:shadow_metal_block");
        setBlockName("shadow_metal_block");
        this.setHarvestLevel("pickaxe", 2);
        this.setStepSound(Blocks.iron_block.stepSound);
        setHardness(300);
    }

    @Override
    public float getPlayerRelativeBlockHardness(EntityPlayer p_149737_1_, World p_149737_2_, int p_149737_3_, int p_149737_4_, int p_149737_5_) {
        return 0.9f;

    }

    @Override
    public float getBlockHardness(World p_149712_1_, int p_149712_2_, int p_149712_3_, int p_149712_4_) {
        return 3000000;
    }

    @Override
    public float getExplosionResistance(Entity p_149638_1_) {
        return 9000;
    }
}

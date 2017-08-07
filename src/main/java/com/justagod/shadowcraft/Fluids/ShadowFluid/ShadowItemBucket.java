package com.justagod.shadowcraft.Fluids.ShadowFluid;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

import static com.justagod.shadowcraft.ShadowCraft.MODID;

/**
 * Драсьте, сделано Yuri
 * В 14:25
 */
public class ShadowItemBucket extends ItemBucket {


    public ShadowItemBucket(Block block) {
        super(block);
        setTextureName(MODID + ':' + "shadow_fluid_bucket");
        setCreativeTab(ShadowCraft.items);
    }
}

package com.justagod.shadowcraft.misc;

import com.justagod.shadowcraft.item.ShadowWand;
import net.minecraft.block.Block;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 17:17
 */
public interface Wandable {

    boolean onWand(ShadowWand.WandWrapper wrapper, World world, Block block, int x, int y, int z, int meta);
}

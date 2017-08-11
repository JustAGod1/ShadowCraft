package com.justagod.shadowcraft.fluid;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.FillBucketEvent;

/**
 * Драсьте, сделано Yuri
 * В 17:20
 */
public class OnBucketRightClickListener {

    @SubscribeEvent
    public void onBucket(FillBucketEvent e) {
        Block block = e.world.getBlock(
                e.target.blockX,
                e.target.blockY,
                e.target.blockZ);

        if (block != null) {
            if (block instanceof AbstractFluidBlock) {
                e.result = ((AbstractFluidBlock) block).getBucket(
                        e.world,
                        e.target.blockX,
                        e.target.blockY,
                        e.target.blockZ
                );
                e.world.setBlockToAir(
                        e.target.blockX,
                        e.target.blockY,
                        e.target.blockZ
                );
                e.setResult(Event.Result.ALLOW);
            }
        }
    }
}

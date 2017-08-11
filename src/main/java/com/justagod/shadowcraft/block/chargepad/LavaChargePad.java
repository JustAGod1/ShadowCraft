package com.justagod.shadowcraft.block.chargepad;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

/**
 * Created by Yuri on 11.07.17.
 */
public class LavaChargePad extends ChargePad {

    private final int MAX_DIST = 2;

    public LavaChargePad() {
        super();
        setBlockName("lava_charge_pad");
    }

    @Override
    public float getSpeedMultiplier(World world, int x, int y, int z) {
        return 5;
    }

    @Override
    public boolean isCanRecharge(World world, int startX, int startY, int startZ) {

        int count = 0;

        for (int x = startX - MAX_DIST; x <= startX + MAX_DIST; x++) {
            for (int z = startZ - MAX_DIST; z <= startZ + MAX_DIST; z++) {
                count += isHaveLavaColumnAt(world, x, startY, z)?1:0;
            }
        }

        return count >= 6;
    }

    private boolean isHaveLavaColumnAt(World world, int x, int y, int z) {
        if (world.getBlock(x, y, z) != Blocks.lava) return false;
        if (world.getBlock(x, y + 1, z) != Blocks.lava) return false;
        if (world.getBlock(x, y + 2, z) != Blocks.lava) return false;
        return true;
    }
}

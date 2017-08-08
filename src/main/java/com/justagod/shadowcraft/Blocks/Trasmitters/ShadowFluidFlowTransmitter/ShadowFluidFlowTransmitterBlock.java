package com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter;

import com.justagod.shadowcraft.Flows.FlowTransmitter;
import com.justagod.shadowcraft.Flows.FlowTransmitterEntity;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 15:43
 */
public class ShadowFluidFlowTransmitterBlock extends FlowTransmitter {

    public ShadowFluidFlowTransmitterBlock() {
        super(Material.anvil);
        setBlockBounds(0, 0.6f, 0, 1, 1, 1);
    }

    @Override
    protected FlowTransmitterEntity getFlowTransmitterEntity(World world, int meta) {
        return new ShadowFluidFlowTransmitterTile();
    }

    @Override
    public boolean canPlaceBlockAt(World world, int fx, int y, int fz) {
        for (int x = fx - 2; x <= fx + 2; x++) {
            for (int z = fz - 2; z <= fz + 2; z++) {
                if (world.getBlock(x, y, z) == ShadowCraft.shadow_fluid_transmitter_block) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}

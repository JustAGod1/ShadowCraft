package com.justagod.shadowcraft.Blocks.Grower;

import com.justagod.shadowcraft.Flows.FlowReceiver;
import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 14:53
 */
public class GrowerBlock extends FlowReceiver {

    public GrowerBlock() {
        // TODO: 09.08.17 Сделать нормальный материал для Взращевателя
        super(Material.anvil);
        setLightOpacity(1);
        setBlockBounds(0, 0, 0, 1, 0.025f * 2, 1);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean needDrawFlowEffects(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public FlowReceiverEntity getFlowReceiverEntity(World world, int meta) {
        return new GrowerTile();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (side == 1) {
                GrowerTile entity = (GrowerTile) world.getTileEntity(x, y, z);

            }
        }

        return true;

    }
}

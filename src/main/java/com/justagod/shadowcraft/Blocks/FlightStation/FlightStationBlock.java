package com.justagod.shadowcraft.Blocks.FlightStation;

import com.justagod.shadowcraft.Blocks.ShadowBlock;
import com.justagod.shadowcraft.Flows.FlowReceiver;
import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by Yuri on 26.07.17.
 */
public class FlightStationBlock extends FlowReceiver {

    public FlightStationBlock() {
        // TODO: 26.07.17 Сделать нормальный материал для станции полетов
        super(Material.clay);
        setBlockName("flight_station");
    }

    @Override
    public boolean needDrawFlowEffects(World world, int x, int y, int z) {
        return true;
    }

    @Override
    public FlowReceiverEntity getFlowReceiverEntity(World world, int meta) {
        return new FlightStationEntity();
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}

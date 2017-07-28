package com.justagod.shadowcraft.Blocks.AdminShadowFlowTransmitter;

import com.justagod.shadowcraft.Flows.FlowTransmitter;
import com.justagod.shadowcraft.Flows.FlowTransmitterEntity;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by Yuri on 27.07.17.
 */
public class AdminShadowFlowTransmitterBlock extends FlowTransmitter {

    public AdminShadowFlowTransmitterBlock() {
        // TODO: 27.07.17 Материал для админского трансмитера
        super(Material.anvil);
        setBlockName("admin_shadow_flow_transmitter");
    }

    @Override
    protected FlowTransmitterEntity getFlowTransmitterEntity(World world, int meta) {
        return new AdminShadowFlowTransmitterEntity();
    }
}

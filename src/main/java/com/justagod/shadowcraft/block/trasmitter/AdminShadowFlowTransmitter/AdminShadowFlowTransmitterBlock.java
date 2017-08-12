package com.justagod.shadowcraft.block.trasmitter.AdminShadowFlowTransmitter;

import com.justagod.shadowcraft.misc.flow.FlowTransmitter;
import com.justagod.shadowcraft.misc.flow.FlowTransmitterEntity;
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

package com.justagod.shadowcraft.Network;

import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationRender;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterRender;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperRenderer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;

/**
 * Created by Yuri on 26.07.17.
 */
public class ClientProxy extends CommonProxy {

    public void registerrenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(WitherReplacerEntity.class, new WitherReplacerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(LavaShadowFlowTransmitterEntity.class, new LavaShadowFlowTransmitterRender());
        ClientRegistry.bindTileEntitySpecialRenderer(WebZapperEntity.class, new WebZapperRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(FlightStationEntity.class, new FlightStationRender());

    }
}

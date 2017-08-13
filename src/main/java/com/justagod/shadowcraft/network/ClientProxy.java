package com.justagod.shadowcraft.network;

import com.justagod.shadowcraft.block.flightstation.FlightStationEntity;
import com.justagod.shadowcraft.block.flightstation.FlightStationItemRender;
import com.justagod.shadowcraft.block.flightstation.FlightStationRender;
import com.justagod.shadowcraft.block.grower.GrowerItemRender;
import com.justagod.shadowcraft.block.grower.GrowerRender;
import com.justagod.shadowcraft.block.grower.GrowerTile;
import com.justagod.shadowcraft.block.shadowcore.ShadowCoreRender;
import com.justagod.shadowcraft.block.shadowcore.ShadowCoreTile;
import com.justagod.shadowcraft.block.stringscreator.StringsCreatorEntity;
import com.justagod.shadowcraft.block.stringscreator.StringsCreatorRender;
import com.justagod.shadowcraft.block.trasmitter.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.block.trasmitter.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterRender;
import com.justagod.shadowcraft.block.trasmitter.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterRender;
import com.justagod.shadowcraft.block.trasmitter.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterTile;
import com.justagod.shadowcraft.block.webzapper.WebZapperEntity;
import com.justagod.shadowcraft.block.webzapper.WebZapperRenderer;
import com.justagod.shadowcraft.block.witherreplacer.CaptionLabelRenderer;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerRenderer;
import com.justagod.shadowcraft.EntityFactory;
import com.justagod.shadowcraft.ShadowCraft;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Yuri on 26.07.17.
 */
public class ClientProxy extends CommonProxy {

    public void registerRenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(WitherReplacerEntity.class, new WitherReplacerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(LavaShadowFlowTransmitterEntity.class, new LavaShadowFlowTransmitterRender());
        ClientRegistry.bindTileEntitySpecialRenderer(WebZapperEntity.class, new WebZapperRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(FlightStationEntity.class, new FlightStationRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ShadowCoreTile.class, new ShadowCoreRender());
        ClientRegistry.bindTileEntitySpecialRenderer(ShadowFluidFlowTransmitterTile.class, new ShadowFluidFlowTransmitterRender());
        ClientRegistry.bindTileEntitySpecialRenderer(StringsCreatorEntity.class, new StringsCreatorRender());
        ClientRegistry.bindTileEntitySpecialRenderer(GrowerTile.class, new GrowerRender());

        MinecraftForge.EVENT_BUS.register(new CaptionLabelRenderer());
        MinecraftForge.EVENT_BUS.register(new EntityFactory());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ShadowCraft.flight_station_block), new FlightStationItemRender());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ShadowCraft.grower), new GrowerItemRender());
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {

        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }


}

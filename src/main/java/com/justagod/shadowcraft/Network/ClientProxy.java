package com.justagod.shadowcraft.Network;

import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationItemRender;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationRender;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterRender;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperRenderer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.CaptionLabelRenderer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerRenderer;
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

    public void registerrenders() {
        ClientRegistry.bindTileEntitySpecialRenderer(WitherReplacerEntity.class, new WitherReplacerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(LavaShadowFlowTransmitterEntity.class, new LavaShadowFlowTransmitterRender());
        ClientRegistry.bindTileEntitySpecialRenderer(WebZapperEntity.class, new WebZapperRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(FlightStationEntity.class, new FlightStationRender());

        MinecraftForge.EVENT_BUS.register(new CaptionLabelRenderer());
        MinecraftForge.EVENT_BUS.register(new EntityFactory());

        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ShadowCraft.flightStationBlock), new FlightStationItemRender());
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }


}

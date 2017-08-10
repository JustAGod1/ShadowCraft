package com.justagod.shadowcraft.Network;

import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationItemRender;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationRender;
import com.justagod.shadowcraft.Blocks.Grower.GrowerItemRender;
import com.justagod.shadowcraft.Blocks.Grower.GrowerRender;
import com.justagod.shadowcraft.Blocks.Grower.GrowerTile;
import com.justagod.shadowcraft.Blocks.ShadowCore.ShadowCoreRender;
import com.justagod.shadowcraft.Blocks.ShadowCore.ShadowCoreTile;
import com.justagod.shadowcraft.Blocks.StringsCreator.StringsCreatorEntity;
import com.justagod.shadowcraft.Blocks.StringsCreator.StringsCreatorRender;
import com.justagod.shadowcraft.Blocks.Trasmitters.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.Trasmitters.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterRender;
import com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterRender;
import com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterTile;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperRenderer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.CaptionLabelRenderer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerRenderer;
import com.justagod.shadowcraft.EntityFactory;
import com.justagod.shadowcraft.Items.CraftingItems.ShadowCore;
import com.justagod.shadowcraft.ShadowCraft;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
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
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }


}

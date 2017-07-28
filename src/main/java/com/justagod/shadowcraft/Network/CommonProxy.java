package com.justagod.shadowcraft.Network;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.S14PacketEntity;

/**
 * Created by Yuri on 26.07.17.
 */
public class CommonProxy {

    public EntityPlayer getPlayerEntity(MessageContext ctx ) {
        return ctx.getServerHandler().playerEntity;
    }

}

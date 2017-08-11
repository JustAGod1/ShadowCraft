package com.justagod.shadowcraft.network;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Yuri on 26.07.17.
 */
public class CommonProxy {

    public EntityPlayer getPlayerEntity(MessageContext ctx ) {
        return ctx.getServerHandler().playerEntity;
    }

}

package com.justagod.shadowcraft;

import com.justagod.shadowcraft.network.PacketHandler;
import com.justagod.shadowcraft.network.packet.TellersSyncPacket;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Драсьте, сделано Yuri
 * В 0:34
 */
public class SCEventHandler {

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            PacketHandler.INSTANCE.sendTo(new TellersSyncPacket(), (EntityPlayerMP) event.player);
        }
    }
}

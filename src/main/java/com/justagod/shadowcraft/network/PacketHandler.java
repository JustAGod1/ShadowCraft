package com.justagod.shadowcraft.network;

import com.justagod.shadowcraft.network.packet.TellerToClientPacket;
import com.justagod.shadowcraft.network.packet.TellersSyncPacket;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * Created by Yuri on 28.07.17.
 */
public class PacketHandler {

    public static final SimpleNetworkWrapper INSTANCE;


    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("shadowcraft_mod");

        int idx = 0;
        INSTANCE.registerMessage(TellerToClientPacket.class, TellerToClientPacket.class, idx++, Side.CLIENT);
        INSTANCE.registerMessage(TellersSyncPacket.class, TellersSyncPacket.class, idx, Side.SERVER);
    }
}

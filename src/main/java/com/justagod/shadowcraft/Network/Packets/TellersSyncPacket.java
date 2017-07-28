package com.justagod.shadowcraft.Network.Packets;

import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Utils.Vector3;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import org.lwjgl.opencl.CL;

import java.util.LinkedList;
import java.util.List;

import static com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity.CLIENT_INSTANCES;
import static com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity.SERVER_INSTANCES;

/**
 * Драсьте, сделано Yuri
 * В 0:55
 */
public class TellersSyncPacket implements IMessage, IMessageHandler<TellersSyncPacket, IMessage> {

    private List<Vector3> poses = new LinkedList<Vector3>();

    @Override
    public void fromBytes(ByteBuf buf) {
        final long size = buf.readLong();

        for (int i = 0; i < size; i++) {
            poses.add(Vector3.readFromByteBuf(buf));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(SERVER_INSTANCES.size());
        for (WitherReplacerEntity tile : SERVER_INSTANCES) {
            Vector3 vec = new Vector3(tile.xCoord, tile.yCoord, tile.zCoord);
            vec.writeToByteBuf(buf);
        }
    }

    @Override
    public IMessage onMessage(TellersSyncPacket message, MessageContext ctx) {
        for (Vector3 pos : message.poses) {
            CLIENT_INSTANCES.clear();
            CLIENT_INSTANCES.add((WitherReplacerEntity) Minecraft.getMinecraft().theWorld.getTileEntity(
                    pos.getXInt(),
                    pos.getYInt(),
                    pos.getZInt()
            ));
        }

        return null;
    }
}

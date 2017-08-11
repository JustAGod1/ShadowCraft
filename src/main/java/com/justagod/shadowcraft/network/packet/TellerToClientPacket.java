package com.justagod.shadowcraft.network.packet;

import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerEntity;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;

/**
 * Драсьте, сделано Yuri
 * В 22:43
 */
public class TellerToClientPacket implements IMessage, IMessageHandler<TellerToClientPacket, IMessage> {

    private int x;
    private int y;
    private int z;

    public TellerToClientPacket() {
    }

    public TellerToClientPacket(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public IMessage onMessage(TellerToClientPacket message, MessageContext ctx) {

            try {
                WitherReplacerEntity.CLIENT_INSTANCES.add((WitherReplacerEntity) Minecraft.getMinecraft().theWorld.getTileEntity(
                        message.x,
                        message.y,
                        message.z
                        )
                );
            }
            catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        return null;
    }
}

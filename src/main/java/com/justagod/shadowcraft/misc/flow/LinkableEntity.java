package com.justagod.shadowcraft.misc.flow;

import com.justagod.shadowcraft.util.Vector3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yuri on 18.07.17.
 */
public abstract class LinkableEntity extends TileEntity {

    public abstract void onLinked(Vector3 linkPos, Linkable linkBlock);

    public abstract void onUnlinked(Vector3 linkPos);

    public abstract void onBlockDestroy();

    @Override
    public final void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());

    }

    @Override
    public final Packet getDescriptionPacket() {
        NBTTagCompound tagCom = new NBTTagCompound();
        this.writeToNBT(tagCom);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tagCom);
    }
}

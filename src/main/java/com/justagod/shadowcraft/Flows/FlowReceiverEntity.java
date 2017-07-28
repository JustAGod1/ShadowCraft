package com.justagod.shadowcraft.Flows;

import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuri on 18.07.17.
 */
public class FlowReceiverEntity extends LinkableEntity {

    private static final String TRANSMITTERS_TAG = "transmitters";
    private static final String TRANSMITTER_X = "x";
    private static final String TRANSMITTER_Y = "y";
    private static final String TRANSMITTER_Z = "z";

    private final Map<Vector3, FlowTransmitter> transmitters = new HashMap<Vector3, FlowTransmitter>();
    private final List<Vector3> tmpArray = new ArrayList<Vector3>();
    private boolean isTransmittersFilled = false;

    public void addTransmitter(Vector3 pos, FlowTransmitter transmitter) {
        transmitters.put(pos, transmitter);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    public void removeTransmitter(Vector3 pos) {
        transmitters.remove(pos);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!isTransmittersFilled && hasWorldObj()) {
            for (Vector3 vector3 : tmpArray) {
                noteTransmitter((int) vector3.getX(), (int) vector3.getY(), (int) vector3.getZ());
            }

            isTransmittersFilled = true;
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
        }
    }

    @Override
    public void setWorldObj(World world) {
        super.setWorldObj(world);

    }

    private void noteTransmitter(int x, int y, int z) {
        Block block = worldObj.getBlock(x, y, z);
        if (block instanceof FlowTransmitter) {
            Vector3 vector = new Vector3(x, y, z);

            transmitters.put(vector, (FlowTransmitter) block);
        }
    }

    public boolean isTransmittersFilled() {
        return isTransmittersFilled;
    }

    public int getShadowFlowsCount() {
        int count = 0;
        for (Map.Entry<Vector3, FlowTransmitter> entry : transmitters.entrySet()) {
            count += entry.getValue().getShadowFlowsCount(worldObj, (int) entry.getKey().getX(), (int) entry.getKey().getY(), (int) entry.getKey().getZ());
        }

        return count;
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList transmitters = new NBTTagList();

        for (Vector3 pos : this.transmitters.keySet()) {
            NBTTagCompound transmitter = new NBTTagCompound();

            transmitter.setInteger(TRANSMITTER_X, (int) pos.getX());
            transmitter.setInteger(TRANSMITTER_Y, (int) pos.getY());
            transmitter.setInteger(TRANSMITTER_Z, (int) pos.getZ());

            transmitters.appendTag(transmitter);
        }

        compound.setTag(TRANSMITTERS_TAG, transmitters);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        isTransmittersFilled = false;
        transmitters.clear();
        tmpArray.clear();

        NBTTagList tagList = (NBTTagList) compound.getTag(TRANSMITTERS_TAG);

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound transmitter = tagList.getCompoundTagAt(i);

            tmpArray.add(new Vector3(
                    transmitter.getInteger(TRANSMITTER_X),
                    transmitter.getInteger(TRANSMITTER_Y),
                    transmitter.getInteger(TRANSMITTER_Z)
            ));
        }
    }

    @Override
    public void onLinked(Vector3 linkPos, Linkable linkBlock) {
        transmitters.put(linkPos, (FlowTransmitter) linkBlock);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();
    }

    @Override
    public void onUnlinked(Vector3 linkPos) {
        transmitters.remove(linkPos);
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
        this.markDirty();

    }

    @Override
    public void onBlockDestroy() {
        for (Map.Entry<Vector3, FlowTransmitter> entry : transmitters.entrySet()) {
            entry.getValue().onLinkedBlockDestroyed(worldObj, new Vector3(xCoord, yCoord, zCoord), entry.getKey());
        }
    }

}

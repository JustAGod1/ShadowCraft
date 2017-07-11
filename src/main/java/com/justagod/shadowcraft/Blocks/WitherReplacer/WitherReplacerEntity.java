package com.justagod.shadowcraft.Blocks.WitherReplacer;

import com.justagod.shadowcraft.Flows.FlowTransmitter;
import com.justagod.shadowcraft.ShadowCrystals.ShadowCrystal;
import com.justagod.shadowcraft.Utils.Vector3;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuri on 06.07.17.
 */
public class WitherReplacerEntity extends TileEntity {

    public static final List<WitherReplacerEntity> instances = new ArrayList<WitherReplacerEntity>();

    private static final String CRYSTAL_TAG = "crystal";
    private static final String IS_HAVE_CRYSTAL_TAG = "is_have_crystal";
    private static final String TRANSMITTERS_TAG = "transmitters";
    private static final String TRANSMITTERS_COUNT_TAG = "transmitters_count";
    private static final String TRANSMITTER_ENTRY_TAG = "transmitter";
    private static final String TRANSMITTER_X = "x";
    private static final String TRANSMITTER_Y = "y";
    private static final String TRANSMITTER_Z = "z";


    private final String caption = "JustAGod - красавчик";
    private final Map<Vector3, FlowTransmitter> transmitters = new HashMap<Vector3, FlowTransmitter>();
    private final List<Vector3> tmpArray = new ArrayList<Vector3>();
    private boolean isTransmittersFilled = false;
    private ItemStack crystal;

    public WitherReplacerEntity() {
        System.out.println("Entity created");

        instances.add(this);
    }

    public String getCaption() {
        if (!crystal.hasDisplayName()) {
            return caption;
        } else {
            return crystal.getDisplayName();
        }
    }

    public synchronized ItemStack getCrystal() {
        return crystal;
    }

    public synchronized void setCrystal(ItemStack crystal) {
        this.crystal = crystal;
    }

    public float getCurrentMaxDistance() {
        if (!isTransmittersFilled) return -1;
        if (crystal == null) return -1;
        if (((ShadowCrystal) crystal.getItem()).getRequiredShadowFlows() > getShadowFlowsCount()) return -1;
        return (float) ((ShadowCrystal) crystal.getItem()).getTellRadius();
    }

    public void addTransmitter(Vector3 pos, FlowTransmitter transmitter) {
        transmitters.put(pos, transmitter);
    }

    public void removeTransmitter(Vector3 pos) {
        transmitters.remove(pos);
    }

    @Override
    public void updateEntity() {
        for (int i = 0; i < instances.size(); i++) {
            WitherReplacerEntity entity = instances.get(i);

            if ((entity.xCoord == xCoord) && (entity.yCoord == yCoord) && (entity.zCoord == zCoord) && (entity != this) && (instances.contains(this))) {
                instances.remove(i);
            }
        }

        if (!isTransmittersFilled && hasWorldObj()) {
            for (Vector3 vector3 : tmpArray) {
                noteTransmitter((int) vector3.getX(), (int) vector3.getY(), (int) vector3.getZ());
            }

            isTransmittersFilled = true;
        }

    }

    @Override
    public boolean equals(Object obj) {
        return obj.toString().equals(toString());
    }


    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(
                xCoord - 1,
                yCoord - 1,
                zCoord - 1,
                xCoord + 2,
                yCoord + 2,
                zCoord + 2);
    }

    private int getShadowFlowsCount() {
        int count = 0;
        for (Map.Entry<Vector3, FlowTransmitter> entry : transmitters.entrySet()) {
            count += entry.getValue().getShadowFlowsCount(worldObj, (int) entry.getKey().getX(), (int) entry.getKey().getY(), (int) entry.getKey().getZ());
        }

        return count;
    }


    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);


        if (crystal != null) {
            NBTTagCompound crystalCompound = new NBTTagCompound();
            crystal.writeToNBT(crystalCompound);
            compound.setTag(CRYSTAL_TAG, crystalCompound);

            compound.setBoolean(IS_HAVE_CRYSTAL_TAG, true);
        } else {
            compound.setBoolean(IS_HAVE_CRYSTAL_TAG, false);
        }

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

        if (compound.getBoolean(IS_HAVE_CRYSTAL_TAG)) {
            NBTTagCompound crystalCompound = (NBTTagCompound) compound.getTag(CRYSTAL_TAG);
            this.crystal = ItemStack.loadItemStackFromNBT(crystalCompound);
        }

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
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());

    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCom = new NBTTagCompound();
        this.writeToNBT(tagCom);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, this.blockMetadata, tagCom);
    }

    private void noteTransmitter(int x, int y, int z) {
        Block block = worldObj.getBlock(x, y, z);
        if (block instanceof FlowTransmitter) {
            Vector3 vector = new Vector3(x, y, z);

            transmitters.put(vector, (FlowTransmitter) block);
        }
    }
}

package com.justagod.shadowcraft.Items.Absorbents;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Yuri on 25.07.17.
 */
public class AbsorbentsData implements IExtendedEntityProperties {

    public static final int MAX_VALUE = 20;

    private long pumpTime = -1L;



    public int getValue(final World world) {
        if (pumpTime == -1) {
            return MAX_VALUE;
        } else if (pumpTime + 1000 <= world.getWorldTime()) {
            return MAX_VALUE;
        } else {
            return 0;
        }
    }

    public int pump(final World world) {
        if (pumpTime == -1) {
            pumpTime = world.getWorldTime();
            return MAX_VALUE;
        } else if (pumpTime + 1000 <= world.getWorldTime()) {
            pumpTime = world.getWorldTime();
            return MAX_VALUE;
        } else {
            return 0;
        }
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setLong("pump_time", pumpTime);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        pumpTime = compound.getLong("pump_time");
    }

    @Override
    public void init(Entity entity, World world) {

    }
}

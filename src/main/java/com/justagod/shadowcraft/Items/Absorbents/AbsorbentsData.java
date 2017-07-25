package com.justagod.shadowcraft.Items.Absorbents;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Yuri on 25.07.17.
 */
public abstract class AbsorbentsData implements IExtendedEntityProperties, ITickable {

    public static final int MAX_VALUE = 20;

    protected int value = MAX_VALUE;
    private int ticks = 0;

    public AbsorbentsData() {

    }

    public AbsorbentsData(int value) {
        this();
        this.value = value;
    }

    protected abstract String getSpecialKey();

    public int getValue() {
        return value;
    }

    public int pump() {
        int tmp = value;
        value = 0;
        return tmp;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {
        compound.setInteger(getSpecialKey(), value);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {
        value = compound.getInteger(getSpecialKey());
    }

    @Override
    public void init(Entity entity, World world) {

    }

    @Override
    public void tick() {
        ticks++;

        if (ticks % 400 == 0) {
            if (value < MAX_VALUE) {
                value++;
            }
        }
    }
}

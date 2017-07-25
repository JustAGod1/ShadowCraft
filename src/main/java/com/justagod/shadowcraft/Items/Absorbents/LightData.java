package com.justagod.shadowcraft.Items.Absorbents;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

/**
 * Created by Yuri on 25.07.17.
 */
public class LightData extends AbsorbentsData {
    @Override
    protected String getSpecialKey() {
        return null;
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

    }

    @Override
    public void init(Entity entity, World world) {

    }
}

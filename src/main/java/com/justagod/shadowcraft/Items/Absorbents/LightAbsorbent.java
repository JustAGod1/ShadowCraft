package com.justagod.shadowcraft.Items.Absorbents;

import com.justagod.shadowcraft.Items.ShadowItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Created by Yuri on 25.07.17.
 */
public class LightAbsorbent extends Absorbent {

    @Override
    protected String getDataTag() {
        return "shadowcraft:light_left";
    }

    @Override
    protected ItemStack onPump(ItemStack stack, World world, EntityPlayer player, AbsorbentsData data) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();

        int currentLight = compound.getInteger("light");

        if (currentLight + data.getValue() >= NEED_TO_ABSORB) {
            // TODO: 25.07.17 Добавить абсорбированый свет
            return null;
        } else {
            compound.setInteger("light", currentLight + data.getValue());
            return stack;
        }


    }

}

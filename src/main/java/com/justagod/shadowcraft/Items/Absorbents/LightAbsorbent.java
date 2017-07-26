package com.justagod.shadowcraft.Items.Absorbents;

import com.justagod.shadowcraft.Items.ShadowItem;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Yuri on 25.07.17.
 */
public class LightAbsorbent extends Absorbent {

    public LightAbsorbent() {
        super();
        setUnlocalizedName("light_absorbent");
        setMaxStackSize(1);
    }

    @Override
    protected String getDataTag() {
        return "shadowcraft:light_data";
    }

    @Override
    protected ItemStack onPump(ItemStack stack, World world, EntityPlayer player, AbsorbentsData data) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();

        int currentLight = compound.getInteger("light");

        if (currentLight + data.getValue(world) >= NEED_TO_ABSORB) {
            data.pump(world);
            return new ItemStack(ShadowCraft.absorbedLight);
        } else {
            compound.setInteger("light", currentLight + data.pump(world));
            return stack;
        }


    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean equiped) {
        super.addInformation(stack, player, list, equiped);
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();
        int absorbed = compound.getInteger("light");

        list.add(I18n.format("shadowcraft.absorbent.absorbed_shadow", absorbed, NEED_TO_ABSORB));
    }

}

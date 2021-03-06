package com.justagod.shadowcraft.item.absorbent;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Yuri on 25.07.17.
 */
public class ShadowAbsorbent extends Absorbent {

    public ShadowAbsorbent() {
        super();
        setUnlocalizedName("shadow_absorbent");
        setTextureName("shadowcraft:shadow_absorbent");
        setMaxStackSize(1);

    }

    @Override
    protected String getDataTag() {
        return "shadowcraft:shadow_data";
    }

    @Override
    protected ItemStack onPump(ItemStack stack, World world, EntityPlayer player, AbsorbentsData data) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();

        int currentShadow = compound.getInteger("shadow");

        if (currentShadow + data.getValue(world) >= NEED_TO_ABSORB) {
            data.pump(world);
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1000, 4, true));
            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1000, 2, true));
            return new ItemStack(ShadowCraft.absorbed_shadow);
        } else {
            compound.setInteger("shadow", currentShadow + data.pump(world));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1000, 4, true));
            player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1000, 2, true));
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
        int absorbed = compound.getInteger("shadow");

        list.add(I18n.format("shadowcraft.absorbent.absorbed_shadow", absorbed, NEED_TO_ABSORB));
    }
}

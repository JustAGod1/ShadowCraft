package com.justagod.shadowcraft.Items.Absorbents;

import com.justagod.shadowcraft.Items.ShadowItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Yuri on 25.07.17.
 */
public abstract class Absorbent extends ShadowItem {

    public static final int NEED_TO_ABSORB = 500;

    protected abstract String getDataTag();
    protected abstract ItemStack onPump(ItemStack stack, World world, EntityPlayer player, AbsorbentsData data);
    // TODO: 25.07.17 Добавить выкачивание света/тени из сущностей
    //protected abstract ItemStack onPumpFrom(ItemStack stack, World world, EntityPlayer player, EntityLivingBase from , AbsorbentsData data);

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            AbsorbentsData data = (AbsorbentsData) player.getExtendedProperties(getDataTag());
            if (data != null) {
                if (data.getValue() > 0) {
                    ItemStack result = onPump(itemStack, world, player, data);
                    return result;
                }
            }

        }
        return itemStack;
    }
}

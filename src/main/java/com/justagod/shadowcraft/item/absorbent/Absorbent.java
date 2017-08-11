package com.justagod.shadowcraft.item.absorbent;

import com.justagod.shadowcraft.item.ShadowItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by Yuri on 25.07.17.
 */
public abstract class Absorbent extends ShadowItem {

    public static final int NEED_TO_ABSORB = 100;

    public Absorbent() {
        setFull3D();
    }

    protected abstract String getDataTag();
    protected abstract ItemStack onPump(ItemStack stack, World world, EntityPlayer player, AbsorbentsData data);
    // TODO: 25.07.17 Добавить выкачивание света/тени из сущностей
    //protected abstract ItemStack onPumpFrom(ItemStack stack, World world, EntityPlayer player, EntityLivingBase from , AbsorbentsData data);

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(String.valueOf(world.getWorldTime())));
            AbsorbentsData data = (AbsorbentsData) player.getExtendedProperties(getDataTag());
            if (data != null) {
                if (data.getValue(world) > 0) {
                    ItemStack result = onPump(itemStack, world, player, data);

                    return result;
                }
            }

        }
        return itemStack;
    }
}

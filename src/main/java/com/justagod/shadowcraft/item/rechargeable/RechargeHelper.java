package com.justagod.shadowcraft.item.rechargeable;

import com.justagod.shadowcraft.block.chargepad.ChargePad;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Yuri on 11.07.17.
 */
public final class RechargeHelper {

    public static void updateItem(EntityItem entityItem) {
        ItemStack stack = entityItem.getEntityItem();

        stack.setItemDamage((int) (stack.getItemDamage() - 0.1 * getRechargeSpeedMultiplier(entityItem.posX, entityItem.posY, entityItem.posZ, entityItem.worldObj)));


    }

    private static float getRechargeSpeedMultiplier(double posX, double posY, double posZ, World worldObj) {
        int x = MathHelper.floor_double(posX);
        int y = MathHelper.floor_double(posY);
        int z = MathHelper.floor_double(posZ);

        Block bottomBlock;

        bottomBlock = worldObj.getBlock(x, y - 1, z);
        if (bottomBlock instanceof ChargePad) {
            return ((ChargePad) bottomBlock).isCanRecharge(worldObj, x, y - 1, z)?((ChargePad) bottomBlock).getSpeedMultiplier(worldObj, x, y - 1, z):0;
        }

        bottomBlock = worldObj.getBlock(x, y, z);
        if (bottomBlock instanceof ChargePad) {
            return ((ChargePad) bottomBlock).isCanRecharge(worldObj, x, y, z)?((ChargePad) bottomBlock).getSpeedMultiplier(worldObj, x, y, z):0;
        }

        return 0;
    }
}

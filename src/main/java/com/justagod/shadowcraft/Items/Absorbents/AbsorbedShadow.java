package com.justagod.shadowcraft.Items.Absorbents;

import com.justagod.shadowcraft.Items.ShadowItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by Yuri on 26.07.17.
 */
public class AbsorbedShadow extends ShadowItem {

    public AbsorbedShadow() {
        setUnlocalizedName("absorbed_shadow");
        setTextureName("shadowcraft:absorbed_shadow");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int meta, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (world.getBlock(x, y, z) == Blocks.bedrock) {
                world.setBlockToAir(x, y, z);
                return true;
            }
            return false;
        }
        return false;
    }
}

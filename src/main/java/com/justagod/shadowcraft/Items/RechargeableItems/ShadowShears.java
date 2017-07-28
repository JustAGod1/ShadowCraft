package com.justagod.shadowcraft.Items.RechargeableItems;

import com.justagod.shadowcraft.Blocks.ChargePads.ChargePad;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.List;

import static com.justagod.shadowcraft.Items.RechargeableItems.RechargeHelper.updateItem;
import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 09.07.17.
 */
public class ShadowShears extends ItemShears {

    public ShadowShears() {
        super();
        setMaxDamage(647);
        setCreativeTab(items);
        setUnlocalizedName("shadow_shears");
        setTextureName("shadowcraft:shadow_shears");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(I18n.format("sc.tooltip.shadow_shears"));
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        updateItem(entityItem);

        return false;
    }


}

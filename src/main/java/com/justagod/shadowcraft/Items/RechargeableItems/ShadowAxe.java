package com.justagod.shadowcraft.Items.RechargeableItems;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemAxe;

import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 11.07.17.
 */
public class ShadowAxe extends ItemAxe {

    public ShadowAxe() {
        super(ShadowCraft.SHADOW_MATERIAL);
        setTextureName("shadowcraft:shadow_axe");
        setUnlocalizedName("shadow_axe");
        setCreativeTab(items);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        RechargeHelper.updateItem(entityItem);

        return false;
    }
}

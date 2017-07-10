package com.justagod.shadowcraft.Items.RechargeableItems;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemHoe;

import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 11.07.17.
 */
public class ShadowHoe extends ItemHoe {

    public ShadowHoe() {
        super(ShadowCraft.SHADOW_MATERIAL);
        setUnlocalizedName("shadow_hoe");
        setTextureName("shadowcraft:shadow_hoe");
        setCreativeTab(items);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        RechargeHelper.updateItem(entityItem);

        return false;
    }
}

package com.justagod.shadowcraft.Items.RechargeableItems;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemPickaxe;

import static com.justagod.shadowcraft.Items.RechargeableItems.RechargeHelper.updateItem;
import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 11.07.17.
 */
public class ShadowPickaxe extends ItemPickaxe {

    public ShadowPickaxe() {
        super(ShadowCraft.SHADOW_MATERIAL);
        setUnlocalizedName("shadow_pickaxe");
        setTextureName("shadowcraft:shadow_pickaxe");
        setCreativeTab(items);


    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        updateItem(entityItem);

        return false;
    }

}

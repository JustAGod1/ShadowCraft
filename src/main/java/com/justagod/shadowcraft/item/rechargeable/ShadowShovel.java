package com.justagod.shadowcraft.item.rechargeable;

import com.google.common.collect.Sets;
import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

import java.util.Set;

import static com.justagod.shadowcraft.item.rechargeable.RechargeHelper.updateItem;
import static com.justagod.shadowcraft.ShadowCraft.SHADOW_MATERIAL;
import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 11.07.17.
 */
public class ShadowShovel extends ItemSpade {

    public ShadowShovel() {
        super(ShadowCraft.SHADOW_MATERIAL);
        setUnlocalizedName("shadow_shovel");
        setTextureName("shadowcraft:shadow_shovel");
        setCreativeTab(items);
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "shadow_shovel_upgrade");
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        updateItem(entityItem);

        return false;
    }

    private class WandUpgrade extends ShadowWand.WandUpgrade {

        private final Set proper_blocks = Sets.newHashSet(Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium);

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.shadow_shovel");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowShovel.this);
        }

        @Override
        public String getToolClass() {
            return "spade";
        }

        @Override
        public float getEfficientOn(ShadowWand.WandWrapper wrapper, Block block) {
            return (block == Blocks.snow_layer || block == Blocks.snow ? SHADOW_MATERIAL.getEfficiencyOnProperMaterial():proper_blocks.contains(block)?SHADOW_MATERIAL.getEfficiencyOnProperMaterial():1);
        }


    }
}

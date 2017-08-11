package com.justagod.shadowcraft.item.rechargeable;

import com.google.common.collect.Sets;
import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

import java.util.Set;

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
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "shadow_axe_upgrade");
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        RechargeHelper.updateItem(entityItem);

        return false;
    }

    public class WandUpgrade extends ShadowWand.WandUpgrade {

        private final Set proper_blocks = Sets.newHashSet(new Block[] {Blocks.planks, Blocks.bookshelf, Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin});

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.shadow_axe");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowAxe.this);
        }

        @Override
        public String getToolClass() {
            return "axe";
        }

        @Override
        public float getEfficientOn(ShadowWand.WandWrapper wrapper, Block block) {
            return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine ? proper_blocks.contains(block)?ShadowCraft.SHADOW_MATERIAL.getEfficiencyOnProperMaterial():1 : ShadowCraft.SHADOW_MATERIAL.getEfficiencyOnProperMaterial();

        }

        @Override
        public int getHarvestLevel(String toolClass) {
            return 4;
        }
    }
}

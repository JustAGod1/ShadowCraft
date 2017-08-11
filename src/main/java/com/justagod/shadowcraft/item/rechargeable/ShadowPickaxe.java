package com.justagod.shadowcraft.item.rechargeable;

import com.google.common.collect.Sets;
import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import java.util.Set;

import static com.justagod.shadowcraft.item.rechargeable.RechargeHelper.updateItem;
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
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "shadow_pickaxe_upgrade");

    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        updateItem(entityItem);

        return false;
    }

    public class WandUpgrade extends ShadowWand.WandUpgrade {

        private final Set proper_blocks = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.shadow_pickaxe");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowPickaxe.this);
        }

        @Override
        public String getToolClass() {
            return "pickaxe";
        }

        @Override
        public float getEfficientOn(ShadowWand.WandWrapper wrapper, Block block) {
            return block.getMaterial() != Material.iron && block.getMaterial() != Material.anvil && block.getMaterial() != Material.rock ? proper_blocks.contains(block)?ShadowCraft.SHADOW_MATERIAL.getEfficiencyOnProperMaterial():1 : ShadowCraft.SHADOW_MATERIAL.getEfficiencyOnProperMaterial();

        }

        @Override
        public int getHarvestLevel(String toolClass) {
            return 4;
        }
    }

}

package com.justagod.shadowcraft.Items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 1:40
 */
public class EarthPlacerUpgradeItem extends ShadowItem {

    public EarthPlacerUpgradeItem() {
        super();
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "earth_placer_upgrade");
        setUnlocalizedName("earth_placer_upgrade");
        setTextureName("shadowcraft:earth_placer_upgrade");
        setMaxStackSize(1);
    }

    public class WandUpgrade extends ShadowWand.WandUpgrade {

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.earth_placer");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(EarthPlacerUpgradeItem.this);
        }

        @Override
        public void onItemUse(ShadowWand.WandWrapper stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            if (!player.isSneaking()) {
                Block block = world.getBlock(x, y, z);

                if (block == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1) {
                    side = 1;
                } else if (block != Blocks.vine && block != Blocks.tallgrass && block != Blocks.deadbush && !block.isReplaceable(world, x, y, z)) {
                    if (side == 0) {
                        --y;
                    }

                    if (side == 1) {
                        ++y;
                    }

                    if (side == 2) {
                        --z;
                    }

                    if (side == 3) {
                        ++z;
                    }

                    if (side == 4) {
                        --x;
                    }

                    if (side == 5) {
                        ++x;
                    }
                }

                if (!player.canPlayerEdit(x, y, z, side, new ItemStack(Blocks.dirt))) {

                } else if (!player.canPlayerEdit(x, y, z, side, new ItemStack(Blocks.dirt))) {
                } else if (y == 255 && Blocks.dirt.getMaterial().isSolid()) {

                } else if (world.canPlaceEntityOnSide(Blocks.dirt, x, y, z, false, side, player, new ItemStack(Blocks.dirt))) {
                    int i1 = 0;
                    int j1 = Blocks.dirt.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, i1);

                    if (placeBlockAt(player, world, x, y, z, side, hitX, hitY, hitZ, j1)) {
                        world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), Blocks.dirt.stepSound.func_150496_b(), (Blocks.dirt.stepSound.getVolume() + 1.0F) / 2.0F, Blocks.dirt.stepSound.getPitch() * 0.8F);

                    }

                }
            }
        }

        public boolean placeBlockAt(EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {

            if (!world.setBlock(x, y, z, Blocks.dirt, metadata, 3)) {
                return false;
            }

            if (world.getBlock(x, y, z) == Blocks.dirt) {
                Blocks.dirt.onBlockPlacedBy(world, x, y, z, player, new ItemStack(Blocks.dirt));
                Blocks.dirt.onPostBlockPlaced(world, x, y, z, metadata);
            }

            return true;
        }
    }
}

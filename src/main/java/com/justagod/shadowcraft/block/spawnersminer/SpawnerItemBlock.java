package com.justagod.shadowcraft.block.spawnersminer;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 18:52
 */
public class SpawnerItemBlock extends Item {

    public SpawnerItemBlock() {
        super();
        setUnlocalizedName("spawner");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
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

        Block spawner = Blocks.mob_spawner;
        if (stack.stackSize == 0) {
            return false;
        } else if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        } else if (y == 255 && spawner.getMaterial().isSolid()) {
            return false;
        } else if (world.canPlaceEntityOnSide(spawner, x, y, z, false, side, player, stack)) {
            int oldMeta = this.getMetadata(stack.getItemDamage());
            int meta = spawner.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, oldMeta);

            if (placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, meta)) {
                TileEntity tile = world.getTileEntity(x, y, z);
                NBTTagCompound compound = stack.getTagCompound().getCompoundTag("tile_data");
                compound.setInteger("x", x);
                compound.setInteger("y", y);
                compound.setInteger("z", z);
                tile.readFromNBT(compound);
                world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), spawner.stepSound.func_150496_b(), (spawner.stepSound.getVolume() + 1.0F) / 2.0F, spawner.stepSound.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return true;
        } else {
            return false;
        }
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        Block spawner = Blocks.mob_spawner;

        if (!world.setBlock(x, y, z, spawner, metadata, 3)) {
            return false;
        }

        if (world.getBlock(x, y, z) == spawner) {
            spawner.onBlockPlacedBy(world, x, y, z, player, stack);
            spawner.onPostBlockPlaced(world, x, y, z, metadata);
        }

        return true;
    }
}

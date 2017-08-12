package com.justagod.shadowcraft.block.spawnersminer;

import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.block.ShadowBlock;
import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.misc.IWandable;
import com.justagod.shadowcraft.util.Vector3;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Драсьте, сделано Yuri
 * В 15:54
 */
public class SpawnersMinerBlock extends ShadowBlock implements IWandable {

    private IIcon[] icons = new IIcon[2];

    public SpawnersMinerBlock() {
        super(Material.iron);
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 0));
        list.add(new ItemStack(item, 1, 1));
    }

    @Override
    public String getUnlocalizedName() {
        return super.getUnlocalizedName();
    }

    @Override
    public int getRenderBlockPass() {
        return 1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        icons[0] = register.registerIcon("shadowcraft:spawners_miner_frame");
        icons[1] = register.registerIcon("shadowcraft:spawners_miner_glass");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return icons[meta];
    }

    @Override
    public boolean onWand(ShadowWand.WandWrapper wrapper, World world, Block block, int x, int y, int z, int meta) {
        if (meta == 1) {
            Vector3 center;
            Block spawner;

            if (world.getBlock(x + 1, y, z) == Blocks.mob_spawner) {
                center = new Vector3(x + 1, y, z);

            } else if (world.getBlock(x + 1, y, z) == Blocks.mob_spawner) {
                center = new Vector3(x - 1, y, z);

            } else if (world.getBlock(x - 1, y, z) == Blocks.mob_spawner) {
                center = new Vector3(x - 1, y, z);

            } else if (world.getBlock(x, y + 1, z) == Blocks.mob_spawner) {
                center = new Vector3(x, y + 1, z);

            } else if (world.getBlock(x, y - 1, z) == Blocks.mob_spawner) {
                center = new Vector3(x, y - 1, z);

            } else if (world.getBlock(x, y, z + 1) == Blocks.mob_spawner) {
                center = new Vector3(x, y, z + 1);

            } else if (world.getBlock(x, y, z - 1) == Blocks.mob_spawner) {
                center = new Vector3(x, y, z - 1);

            } else return false;


            if (checkSurround(world, center.getXInt(), center.getYInt(), center.getZInt())) {


                Item dropItem = ShadowCraft.spawner_item_block;
                ItemStack dropStack = new ItemStack(dropItem);
                NBTTagCompound tileData = new NBTTagCompound();
                TileEntity tile = world.getTileEntity(center.getXInt(), center.getYInt(), center.getZInt());
                tile.writeToNBT(tileData);
                NBTTagCompound compound = new NBTTagCompound();
                compound.setTag("tile_data", tileData);
                dropStack.setTagCompound(compound);
                breakBlocks(world, center.getXInt(), center.getYInt(), center.getZInt());
                EntityItem dropEntity = new EntityItem(world, center.getX() + 0.5, center.getY() + 0.5, center.getZ() + 0.5, dropStack);
                world.spawnEntityInWorld(dropEntity);
                return true;
            } else return false;

        } else return false;

    }

    private void breakBlocks(World world, int startX, int startY, int startZ) {
        for (int y = startY - 1; y <= startY + 1; y++) {
            for (int z = startZ - 1; z <= startZ + 1; z++) {
                for (int x = startX - 1; x <= startX + 1; x++) {
                    world.setBlockToAir(x, y, z);
                }
            }
        }
    }

    private boolean checkSurround(World world, int x, int y, int z) {
        if (!isGlass(world, x, y + 1, z)) return false;
        if (!isGlass(world, x, y - 1, z)) return false;
        if (!isGlass(world, x + 1, y, z)) return false;
        if (!isGlass(world, x - 1, y, z)) return false;
        if (!isGlass(world, x, y, z + 1)) return false;
        if (!isGlass(world, x, y, z - 1)) return false;

        if (!isFrame(world, x, y + 1, z - 1)) return false;
        if (!isFrame(world, x + 1, y + 1, z - 1)) return false;
        if (!isFrame(world, x - 1, y + 1, z - 1)) return false;
        if (!isFrame(world, x, y + 1, z + 1)) return false;
        if (!isFrame(world, x + 1, y + 1, z + 1)) return false;
        if (!isFrame(world, x - 1, y + 1, z + 1)) return false;
        if (!isFrame(world, x - 1, y + 1, z)) return false;
        if (!isFrame(world, x + 1, y + 1, z)) return false;

        if (!isFrame(world, x, y - 1, z - 1)) return false;
        if (!isFrame(world, x + 1, y - 1, z - 1)) return false;
        if (!isFrame(world, x - 1, y - 1, z - 1)) return false;
        if (!isFrame(world, x, y - 1, z + 1)) return false;
        if (!isFrame(world, x + 1, y - 1, z + 1)) return false;
        if (!isFrame(world, x - 1, y - 1, z + 1)) return false;
        if (!isFrame(world, x - 1, y - 1, z)) return false;
        if (!isFrame(world, x + 1, y - 1, z)) return false;

        if (!isFrame(world, x + 1, y, z + 1)) return false;
        if (!isFrame(world, x - 1, y, z + 1)) return false;
        if (!isFrame(world, x + 1, y, z - 1)) return false;
        if (!isFrame(world, x - 1, y, z - 1)) return false;

        return true;
    }

    private boolean isGlass(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        return meta == 1 && block == ShadowCraft.spawners_miner_block;
    }

    private boolean isFrame(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);

        return meta == 0 && block == ShadowCraft.spawners_miner_block;
    }
}

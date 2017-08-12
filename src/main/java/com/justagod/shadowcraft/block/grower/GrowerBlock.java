package com.justagod.shadowcraft.block.grower;

import com.justagod.shadowcraft.misc.flow.FlowReceiver;
import com.justagod.shadowcraft.misc.flow.FlowReceiverEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 14:53
 */
public class GrowerBlock extends FlowReceiver {

    public GrowerBlock() {
        // TODO: 09.08.17 Сделать нормальный материал для Взращевателя
        super(Material.anvil);
        setLightOpacity(1);
        setBlockBounds(0, 0, 0, 1, 0.025f * 2, 1);

        setBlockName("grower");
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack stack) {
        int l = MathHelper.floor_double((double)(livingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;


        world.setBlockMetadataWithNotify(x, y, z, l % 4, 2);

    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean needDrawFlowEffects(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public FlowReceiverEntity getFlowReceiverEntity(World world, int meta) {
        return new GrowerTile();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            ItemStack stack = player.getCurrentEquippedItem();
            GrowerTile entity = (GrowerTile) world.getTileEntity(x, y, z);
            if (stack != null) {


                if (stack.stackSize > 0) {
                    if (entity.tryPutItem(stack)) {
                        stack.splitStack(1);
                    }
                }
            } else {
                if (player.isSneaking()) {
                    ItemStack drop = entity.getResult();

                    if (drop != null) {
                        world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.1, z + 0.5, drop));
                        entity.setInventorySlotContents(3, null);
                    }
                }
            }

        }

        return true;

    }
}

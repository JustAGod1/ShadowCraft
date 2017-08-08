package com.justagod.shadowcraft.Items.CraftingItems;

import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.Items.ShadowItem;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Yuri on 08.07.17.
 */
public class ShadowCore extends ShadowItem {


    public ShadowCore() {
        super();
        setUnlocalizedName("shadow_core");
        setTextureName("shadowcraft:shadow_core");
        setCreativeTab(ShadowCraft.items);

    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side == 1) {
            if (world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z) && world.getBlock(x, y, z).isSideSolid(world, x, y, z, ForgeDirection.UP) && stack.stackSize > 0) {
                world.setBlock(x, y + 1, z, ShadowCraft.shadow_core_block);
                stack.splitStack(1);
            }
        } else if (world.getBlock(x, y, z).isReplaceable(world, x, y, z) && world.getBlock(x, y - 1, z).isSideSolid(world, x, y - 1, z, ForgeDirection.UP) && stack.stackSize > 0) {
            world.setBlock(x, y, z, ShadowCraft.shadow_core_block);
            stack.splitStack(1);
        }

        return true;
    }
}

package com.justagod.shadowcraft.Items;

import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.Utils.Vector3;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Yuri on 08.07.17.
 */
public class ShadowWand extends Item {

    private static final String IS_BLOCK_IN_MEMORY_TAG = "is_block_in_memory";
    private static final String BLOCK_X_TAG = "block_x";
    private static final String BLOCK_Y_TAG = "block_y";
    private static final String BLOCK_Z_TAG = "block_z";


    public ShadowWand() {
        super();
        setUnlocalizedName("shadow_wand");
        setTextureName("shadowcraft:shadow_wand");
        setCreativeTab(ShadowCraft.items);
    }


    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add("Служит для связывания теневых блоков");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        Block block = world.getBlock(x, y, z);

        if (!(block instanceof Linkable)) return false;

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound compound = stack.getTagCompound();


        boolean isBlockInMemory = compound.getBoolean(IS_BLOCK_IN_MEMORY_TAG);

        if (isBlockInMemory) {
            int previousX = compound.getInteger(BLOCK_X_TAG);
            int previousY = compound.getInteger(BLOCK_Y_TAG);
            int previousZ = compound.getInteger(BLOCK_Z_TAG);

            Block previousBlock = world.getBlock(previousX, previousY, previousZ);
            if (!(previousBlock instanceof Linkable)) {
                player.addChatComponentMessage(new ChatComponentTranslation("msg.something_was_changed.txt"));

                compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, false);

                return true;
            }

            Linkable firstLink = (Linkable) block;
            Linkable secondLink = (Linkable) previousBlock;

            player.addChatComponentMessage(new ChatComponentTranslation("msg.second_block_selected.txt"));

            if (firstLink.isValidObjToBind(secondLink) && secondLink.isValidObjToBind(firstLink)) {
                firstLink.onBlockLinked(secondLink, world, new Vector3(previousX, previousY, previousZ), new Vector3(x, y, z));
                secondLink.onBlockLinked(firstLink, world, new Vector3(x, y, z), new Vector3(previousX, previousY, previousZ));

                compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, false);

                player.addChatComponentMessage(new ChatComponentTranslation("msg.bind_completed.txt"));
            } else {
                compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, false);

                player.addChatComponentMessage(new ChatComponentTranslation("msg.unable_to_create_link.txt"));
            }

            return true;
        }

        compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, true);
        compound.setInteger(BLOCK_X_TAG, x);
        compound.setInteger(BLOCK_Y_TAG, y);
        compound.setInteger(BLOCK_Z_TAG, z);

        player.addChatComponentMessage(new ChatComponentTranslation("msg.first_block_selected.txt"));

        return true;

    }
}

package com.justagod.shadowcraft.Blocks.StringsCreator;

import com.justagod.shadowcraft.Blocks.ShadowBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerMaterial;
import com.justagod.shadowcraft.Network.GUIHandler;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yuri on 23.07.17.
 */
public class StringsCreatorBlock extends ShadowBlock implements ITileEntityProvider {

    public StringsCreatorBlock() {
        super(new WitherReplacerMaterial());

        setBlockTextureName("shadowcraft:strings_creater");
        setBlockName("strings_creator");

        opaque = true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {
            player.openGui(ShadowCraft.instance, GUIHandler.STRING_CREATOR_GUI, world, x, y, z);
        }

        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new StringsCreatorEntity();
    }
}

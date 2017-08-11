package com.justagod.shadowcraft.block.stringscreator;

import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerMaterial;
import com.justagod.shadowcraft.flow.FlowReceiver;
import com.justagod.shadowcraft.flow.FlowReceiverEntity;
import com.justagod.shadowcraft.network.GUIHandler;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Yuri on 23.07.17.
 */
public class StringsCreatorBlock extends FlowReceiver {

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
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int getLightValue() {
        return 5;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

    @Override
    public float getAmbientOcclusionLightValue() {
        return 0;
    }

    @Override
    public int getRenderType() {
        return 0;
    }

    @Override
    public boolean needDrawFlowEffects(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public FlowReceiverEntity getFlowReceiverEntity(World world, int meta) {
        return new StringsCreatorEntity();
    }
}

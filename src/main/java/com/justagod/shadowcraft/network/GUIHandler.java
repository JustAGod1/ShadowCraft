package com.justagod.shadowcraft.network;

import com.justagod.shadowcraft.block.stringscreator.StringsCreatorContainer;
import com.justagod.shadowcraft.block.stringscreator.StringsCreatorEntity;
import com.justagod.shadowcraft.block.stringscreator.StringsCreatorGUI;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerContainer;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerGUIContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Yuri on 16.07.17.
 */
public class GUIHandler implements IGuiHandler {

    public static final int WITHER_REPLACER_GUI = 0;
    public static final int STRING_CREATOR_GUI = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case WITHER_REPLACER_GUI:
                return new WitherReplacerContainer((WitherReplacerEntity) world.getTileEntity(x, y, z));
            case STRING_CREATOR_GUI:
                return new StringsCreatorContainer((StringsCreatorEntity) world.getTileEntity(x, y, z), player.inventory);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case WITHER_REPLACER_GUI:
                return new WitherReplacerGUIContainer(new WitherReplacerContainer((WitherReplacerEntity) world.getTileEntity(x, y, z)));
            case STRING_CREATOR_GUI:
                return new StringsCreatorGUI(new StringsCreatorContainer((StringsCreatorEntity) world.getTileEntity(x, y, z), player.inventory));
        }
        return null;
    }
}

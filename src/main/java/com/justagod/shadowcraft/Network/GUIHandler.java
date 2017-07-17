package com.justagod.shadowcraft.Network;

import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerContainer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerGUIContainer;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Yuri on 16.07.17.
 */
public class GUIHandler implements IGuiHandler {

    public static final int WITHER_REPLACER_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case WITHER_REPLACER_GUI:
                return new WitherReplacerContainer((WitherReplacerEntity) world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case WITHER_REPLACER_GUI:
                return new WitherReplacerGUIContainer(new WitherReplacerContainer((WitherReplacerEntity) world.getTileEntity(x, y, z)));
        }
        return null;
    }
}

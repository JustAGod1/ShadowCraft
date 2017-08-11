package com.justagod.shadowcraft.block.witherreplacer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * Created by Yuri on 16.07.17.
 */
public class WitherReplacerContainer extends Container {
    private WitherReplacerEntity entity;

    public WitherReplacerContainer(WitherReplacerEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return entity.isUseableByPlayer(player);
        //return true;
    }

    public WitherReplacerEntity getEntity() {
        return entity;
    }
}

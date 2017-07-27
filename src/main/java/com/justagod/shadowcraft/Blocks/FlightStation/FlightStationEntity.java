package com.justagod.shadowcraft.Blocks.FlightStation;

import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

/**
 * Created by Yuri on 26.07.17.
 */
public class FlightStationEntity extends FlowReceiverEntity {

    @Override
    public void updateEntity() {
        super.updateEntity();


            List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - 60, 0, zCoord - 60, xCoord + 60, 260, zCoord + 60));

            for (EntityPlayer player : players) {
                player.capabilities.allowFlying = true;
            }

    }
}

package com.justagod.shadowcraft.Blocks.FlightStation;

import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Yuri on 26.07.17.
 */
public class FlightStationEntity extends FlowReceiverEntity {

    private final Set<EntityPlayer> currentFlying = new HashSet<EntityPlayer>();
    private int ticks = 0;

    @Override
    public void updateEntity() {
        // TODO: 28.07.17 Полеты между двумя станциями могут быть не корректны
        super.updateEntity();
        if (worldObj.isRemote) {
            ticks++;
        }

        List<EntityPlayer> players = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord - 32, 0, zCoord - 32, xCoord + 32, 260, zCoord + 32));


        Iterator<EntityPlayer> flyingIterator = currentFlying.iterator();
        while (flyingIterator.hasNext()){
            EntityPlayer flying = flyingIterator.next();
            if (!players.contains(flying)) {

                flying.capabilities.allowFlying = false;
                flying.capabilities.isFlying = false;
                flyingIterator.remove();
            }
        }

        int available = getMaxFlightPeople();
        int free = available;
        for (EntityPlayer flyingPlayer : currentFlying) {
            for (EntityPlayer player : players) {
                if (player == flyingPlayer) free--;
            }
        }

        if (free > 0) {
            for (EntityPlayer player : players) {
                if (!player.capabilities.isCreativeMode) {
                    if (!currentFlying.contains(player)) {
                        addFlyingPlayer(player);
                    }
                }
            }
        } else if (free < 0) {
            Iterator<EntityPlayer> playerIterator = currentFlying.iterator();

            while (playerIterator.hasNext() && free < 0) {
                EntityPlayer player = playerIterator.next();
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
                playerIterator.remove();
                free++;
            }
        }



    }

    public int getTicks() {
        return ticks;
    }

    private int getMaxFlightPeople() {
        return getShadowFlowsCount() / 2;
    }

    private void addFlyingPlayer(EntityPlayer player) {
        currentFlying.add(player);
        player.capabilities.allowFlying = true;
    }

}

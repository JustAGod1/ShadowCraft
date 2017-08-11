package com.justagod.shadowcraft.block.witherreplacer;

import com.justagod.shadowcraft.item.shadowcrystal.ShadowCrystal;
import com.justagod.shadowcraft.util.Vector3;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import javax.annotation.Nullable;

import static com.justagod.shadowcraft.block.witherreplacer.WitherReplacerEntity.CLIENT_INSTANCES;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.TEXT;

/**
 * Created by Yuri on 07.07.17.
 */
public class CaptionLabelRenderer {

    public CaptionLabelRenderer() {

    }

    @SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event) {
        if (event.type != TEXT) return;
        Minecraft.getMinecraft().thePlayer.setSneaking(true);

        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;

        float pX = (float) player.posX;
        float pY = (float) player.posY;
        float pZ = (float) player.posZ;

        Vector3 playerPos = new Vector3(pX, pY, pZ);

        WitherReplacerEntity teller = getNearestTeller(playerPos);
        if (teller == null) return;

        Minecraft mc = Minecraft.getMinecraft();

        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = res.getScaledWidth();


        ((ShadowCrystal) teller.getCrystal().getItem()).drawTextAt(teller.getCaption(), width / 2);

    }

    @Nullable
    private WitherReplacerEntity getNearestTeller(Vector3 playerPos) {
        if (CLIENT_INSTANCES.size() <= 0) return null;
        WitherReplacerEntity nearest = CLIENT_INSTANCES.get(0);

        for (int i = 1; i < CLIENT_INSTANCES.size(); i++) {
            WitherReplacerEntity entity = CLIENT_INSTANCES.get(i);
            Vector3 entityPos = new Vector3(entity.xCoord, entity.yCoord, entity.zCoord);
            Vector3 nearestPos = new Vector3(nearest.xCoord, nearest.yCoord, nearest.zCoord);

            if (entityPos.getDistanceTo(playerPos) < nearestPos.getDistanceTo(playerPos)) {
                if (isValid(playerPos, entity)) {
                    nearest = entity;
                    continue;
                }
            }

            if (!isValid(playerPos, nearest) && isValid(playerPos, entity)) {
                nearest = entity;
            }
        }

        if (!isValid(playerPos, nearest)) return null;

        return nearest;
    }

    private boolean isValid(Vector3 playerPos, WitherReplacerEntity entity) {
        Vector3 entityPos = new Vector3(entity.xCoord, entity.yCoord, entity.zCoord);
        double dist = entityPos.getDistanceTo(playerPos);

        return dist <= entity.getCurrentMaxDistance();

    }


}

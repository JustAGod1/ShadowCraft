package com.justagod.shadowcraft.Items.Absorbents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent;

/**
 * Created by Yuri on 25.07.17.
 */
public class EntityFactory {

    @SubscribeEvent
    public void onEntityCreated(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayerMP) {
            if (event.entity.getExtendedProperties("shadowcraft:light_data") == null) {
                event.entity.registerExtendedProperties("shadowcraft:light_data", new AbsorbentsData());
            }
            if (event.entity.getExtendedProperties("shadowcraft:shadow_data") == null) {
                event.entity.registerExtendedProperties("shadowcraft:shadow_data", new AbsorbentsData());
            }
        }

    }
}

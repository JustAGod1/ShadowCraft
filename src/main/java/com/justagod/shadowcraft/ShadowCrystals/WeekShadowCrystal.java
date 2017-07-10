package com.justagod.shadowcraft.ShadowCrystals;

import com.justagod.shadowcraft.ShadowCraft;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created by Yuri on 07.07.17.
 */
public class WeekShadowCrystal extends ShadowCrystal {

    public WeekShadowCrystal() {
        super();
        setUnlocalizedName("weekShadowCrystal");

        setTextureName("shadowcraft:week_crystal");
    }

    @Override
    public double getTellRadius() {
        return 15;
    }

    @Override
    public int getRequiredShadowFlows() {
        return 1;
    }

    @Override
    public void drawTextAt(String text, double pos) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRender = mc.fontRenderer;

        glPushMatrix();
        {
            GL11.glColor3d(1, 1, 1);
            fontRender.drawString(text, (int) (pos - (fontRender.getStringWidth(text) / 2)), 30, 100);
        }
        glPopMatrix();
    }

}

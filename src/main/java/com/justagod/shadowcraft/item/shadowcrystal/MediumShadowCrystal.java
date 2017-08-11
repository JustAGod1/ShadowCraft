package com.justagod.shadowcraft.item.shadowcrystal;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created by Yuri on 07.07.17.
 */
public class MediumShadowCrystal extends ShadowCrystal {

    public MediumShadowCrystal() {
        super();
        setUnlocalizedName("mediumShadowCrystal");

        setTextureName("shadowcraft:medium_crystal");
    }

    @Override
    public double getTellRadius() {
        return 30;
    }

    @Override
    public int getRequiredShadowFlows() {
        return 2;
    }

    @Override
    public void drawTextAt(String text, double pos) {
        Minecraft mc = Minecraft.getMinecraft();
        FontRenderer fontRender = mc.fontRenderer;

        glPushMatrix();
        {
            GL11.glColor3d(1, 1, 1);
            fontRender.drawString("§1" + text, (int) (pos - (fontRender.getStringWidth(text) / 2)), 30, 100);
        }
        glPopMatrix();
    }
}

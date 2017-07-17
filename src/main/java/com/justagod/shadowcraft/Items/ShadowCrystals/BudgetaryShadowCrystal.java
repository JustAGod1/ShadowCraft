package com.justagod.shadowcraft.Items.ShadowCrystals;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created by Yuri on 07.07.17.
 */
public class BudgetaryShadowCrystal extends ShadowCrystal {

    public BudgetaryShadowCrystal() {
        super();
        setUnlocalizedName("budgetaryShadowCrystal");
        setTextureName("shadowcraft:budgetary_crystal");
    }

    @Override
    public double getTellRadius() {
        return 10;
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
            fontRender.drawString("§0" + text, (int) (pos - (fontRender.getStringWidth(text) / 2)), 30, 100);
        }
        glPopMatrix();
    }
}

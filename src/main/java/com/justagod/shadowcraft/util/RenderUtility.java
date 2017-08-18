package com.justagod.shadowcraft.util;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;

/**
 * Драсьте, сделано Yuri
 * В 23:11
 */
public class RenderUtility {

    public static void drawCube(double radius) {
        drawCube(radius, true);
    }

    public static void drawCube(double radius, boolean useTexture) {
        drawCube(radius, radius, radius, useTexture);
    }

    public static void drawCube(double xRadius, double yRadius, double zRadius, boolean useTexture) {
        drawCube(xRadius, yRadius, zRadius, 0, 0, useTexture);
    }

    public static void drawCube(double xRadius, double yRadius, double zRadius, double xOffset, double zOffset, boolean useTexture) {
        Tessellator t = Tessellator.instance;

        t.startDrawingQuads();
        {
            if (useTexture) {
                t.setNormal(0, 1, 0);
                t.addVertexWithUV(-xRadius, yRadius, -zRadius, 0, 1);
                t.addVertexWithUV(-xRadius, yRadius, zRadius, 0, 0);
                t.addVertexWithUV(xRadius, yRadius, zRadius, 1, 0);
                t.addVertexWithUV(xRadius, yRadius, -zRadius, 1, 1);

                t.setNormal(0, -1, 0);
                t.addVertexWithUV(-xRadius + xOffset, -yRadius, -zRadius + zOffset, 0, 1);
                t.addVertexWithUV(xRadius + xOffset, -yRadius, -zRadius + zOffset, 1, 1);
                t.addVertexWithUV(xRadius + xOffset, -yRadius, zRadius + zOffset, 1, 0);
                t.addVertexWithUV(-xRadius + xOffset, -yRadius, zRadius + zOffset, 0, 0);

                t.setNormal(0, 0, -1);
                t.addVertexWithUV(-xRadius, yRadius, -zRadius, 0, 0);
                t.addVertexWithUV(xRadius, yRadius, -zRadius, 1, 0);
                t.addVertexWithUV(xRadius + xOffset, -yRadius, -zRadius + zOffset, 1, 1);
                t.addVertexWithUV(-xRadius + xOffset, -yRadius, -zRadius + zOffset, 0, 1);

                t.setNormal(0, 0, 1);
                t.addVertexWithUV(-xRadius, yRadius, zRadius, 0, 0);
                t.addVertexWithUV(-xRadius + xOffset, -yRadius, zRadius + zOffset, 0, 1);
                t.addVertexWithUV(xRadius + xOffset, -yRadius, zRadius + zOffset, 1, 1);
                t.addVertexWithUV(xRadius, yRadius, zRadius, 1, 0);

                t.setNormal(1, 0, 0);
                t.addVertexWithUV(xRadius, yRadius, -zRadius, 0, 0);
                t.addVertexWithUV(xRadius, yRadius, zRadius, 1, 0);
                t.addVertexWithUV(xRadius + xOffset, -yRadius, zRadius + zOffset, 1, 1);
                t.addVertexWithUV(xRadius + xOffset, -yRadius, -zRadius + zOffset, 0, 1);

                t.setNormal(-1, 0, 0);
                t.addVertexWithUV(-xRadius, yRadius, -zRadius, 0, 0);
                t.addVertexWithUV(-xRadius + xOffset, -yRadius, -zRadius + zOffset, 0, 1);
                t.addVertexWithUV(-xRadius + xOffset, -yRadius, zRadius + zOffset, 1, 1);
                t.addVertexWithUV(-xRadius, yRadius, zRadius, 1, 0);

            } else {
                t.setNormal(0, 1, 0);
                t.addVertex(-xRadius, yRadius, -zRadius);
                t.addVertex(xRadius, yRadius, -zRadius);
                t.addVertex(xRadius, yRadius, zRadius);
                t.addVertex(-xRadius, yRadius, zRadius);

                t.setNormal(0, -1, 0);
                t.addVertex(-xRadius + xOffset, -yRadius, -zRadius + zOffset);
                t.addVertex(xRadius + xOffset, -yRadius, -zRadius + zOffset);
                t.addVertex(xRadius + xOffset, -yRadius, zRadius + zOffset);
                t.addVertex(-xRadius + xOffset, -yRadius, zRadius + zOffset);

                t.setNormal(0, 0, -1);
                t.addVertex(-xRadius, yRadius, -zRadius);
                t.addVertex(xRadius, yRadius, -zRadius);
                t.addVertex(xRadius + xOffset, -yRadius, -zRadius + zOffset);
                t.addVertex(-xRadius + xOffset, -yRadius, -zRadius + zOffset);

                t.setNormal(0, 0, 1);
                t.addVertex(-xRadius, yRadius, zRadius);
                t.addVertex(xRadius, yRadius, zRadius);
                t.addVertex(xRadius + xOffset, -yRadius, zRadius + zOffset);
                t.addVertex(-xRadius + xOffset, -yRadius, zRadius + zOffset);

                t.setNormal(1, 0, 0);
                t.addVertex(xRadius, yRadius, -zRadius);
                t.addVertex(xRadius, yRadius, zRadius);
                t.addVertex(xRadius + xOffset, -yRadius, zRadius + zOffset);
                t.addVertex(xRadius + xOffset, -yRadius, -zRadius + zOffset);

                t.setNormal(-1, 0, 0);
                t.addVertex(-xRadius, yRadius, -zRadius);
                t.addVertex(-xRadius, yRadius, zRadius);
                t.addVertex(-xRadius + xOffset, -yRadius, zRadius + zOffset);
                t.addVertex(-xRadius + xOffset, -yRadius, -zRadius + zOffset);
            }
        }
        t.draw();
    }

    public static void drawItem(Item item, double radius) {
        drawItem(item, radius, radius, 0.01);
    }

    public static void drawItem(double radius) {
        drawItem(radius, radius, 0.01, 0, 0, 1, 1);
    }

    public static void drawItem(Item item, double xyRadius, double zRadius) {
        drawItem(item, xyRadius, xyRadius, zRadius);
    }

    public static void drawItem(Item item, double xRadius, double yRadius, double zRadius) {
        ItemStack stack = new ItemStack(item);
        IIcon iicon = item.getIcon(stack, 1);

        if (iicon == null)
        {
            GL11.glPopMatrix();
            return;
        }

        TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();
        textureManager.bindTexture(textureManager.getResourceLocation(stack.getItemSpriteNumber()));
        TextureUtil.func_152777_a(false, false, 1.0F);
        Tessellator tessellator = Tessellator.instance;
        float minU = iicon.getMinU();
        float maxU = iicon.getMaxU();
        float minV = iicon.getMinV();
        float maxV = iicon.getMaxV();
        float f4 = 0.0F;
        float f5 = 0.3F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        //GL11.glTranslatef(-f4, -f5, 0.0F);
        float f6 = 1.5F;
        //GL11.glScalef(f6, f6, f6);
        //GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
        //GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
        //GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);

        drawItem(xRadius, yRadius, zRadius, minU, minV, maxU, maxV);

    }

    public static void drawItem(double xRadius, double yRadius, double zRadius, double minU, double minV, double maxU, double maxV) {
        Tessellator t = Tessellator.instance;

        glDisable(GL_CULL_FACE);
        t.startDrawingQuads();
        {
            for (double z = -zRadius; z <= zRadius; z += 0.0001) {
                t.addVertexWithUV(-xRadius, -yRadius, z, minU, maxV);
                t.addVertexWithUV(xRadius, -yRadius, z, maxU, maxV);
                t.addVertexWithUV(xRadius, yRadius, z, maxU, minV);
                t.addVertexWithUV(-xRadius, yRadius, z, minU, minV);
            }
        }
        t.draw();
        glEnable(GL_CULL_FACE);
    }

}

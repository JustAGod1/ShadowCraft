package com.justagod.shadowcraft.Utils;

import net.minecraft.client.renderer.Tessellator;

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
}

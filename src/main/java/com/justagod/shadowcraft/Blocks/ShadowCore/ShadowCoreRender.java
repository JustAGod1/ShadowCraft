package com.justagod.shadowcraft.Blocks.ShadowCore;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

/**
 * Драсьте, сделано Yuri
 * В 23:56
 */
public class ShadowCoreRender extends TileEntitySpecialRenderer {

    private ResourceLocation texture = new ResourceLocation("shadowcraft", "textures/items/shadow_core.png");

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float partialTick) {
        Tessellator t = Tessellator.instance;

        bindTexture(texture);

        glPushMatrix();

        glDisable(GL_CULL_FACE);
        glTranslated(x, y, z);

        t.startDrawingQuads();
        {
            for (double ny = 0.001f; ny < 0.1f; ny += 0.0001f) {
                t.setNormal(0, 1, 0);
                t.addVertexWithUV(0.1, ny, 0.1, 0, 1);
                t.addVertexWithUV(0.9, ny, 0.1, 1, 1);
                t.addVertexWithUV(0.9, ny, 0.9, 1, 0);
                t.addVertexWithUV(0.1, ny, 0.9, 0, 0);
            }
        }
        t.draw();

        glEnable(GL_CULL_FACE);
        glPopMatrix();
    }
}

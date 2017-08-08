package com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

/**
 * Драсьте, сделано Yuri
 * В 18:03
 */
public class ShadowFluidFlowTransmitterRender extends TileEntitySpecialRenderer {

    private ResourceLocation beam = new ResourceLocation("shadowcraft", "textures/blocks/white_texture.png");

    @Override
    public void renderTileEntityAt(TileEntity rawEntity, final double tx, final double ty, final double tz, float partialTick) {
        ShadowFluidFlowTransmitterTile entity = (ShadowFluidFlowTransmitterTile) rawEntity;

        glPushMatrix();
        glTranslated(tx, ty, tz);
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (entity.hasShadowFluidAt(x, z)) {
                    drawLaserTo(x, -1, z);
                }
            }
        }
        glPopMatrix();
    }

    private void drawLaserTo(int x, int y, int z) {
        Tessellator t = Tessellator.instance;

        bindTexture(beam);
        glDisable(GL_CULL_FACE);
        t.startDrawingQuads();
        {
            t.addVertexWithUV(0.55, 0.9, 0.55, 1, 0);
            t.addVertexWithUV(x + 0.55, y + 0.5, z + 0.55, 1, 1);
            t.addVertexWithUV(x + 0.45, y + 0.5, z + 0.55, 0, 1);
            t.addVertexWithUV(0.45, 0.9, 0.55, 0, 0);

            t.addVertexWithUV(0.55, 0.9, 0.55, 1, 0);
            t.addVertexWithUV(x + 0.55, y + 0.5, z + 0.55, 1, 1);
            t.addVertexWithUV(x + 0.55, y + 0.5, z + 0.45, 0, 1);
            t.addVertexWithUV(0.55, 0.9, 0.45, 0, 0);

            t.addVertexWithUV(0.55, 0.9, 0.45, 1, 0);
            t.addVertexWithUV(x + 0.55, y + 0.5, z + 0.45, 1, 1);
            t.addVertexWithUV(x + 0.45, y + 0.5, z + 0.45, 0, 1);
            t.addVertexWithUV(0.45, 0.9, 0.45, 0, 0);

            t.addVertexWithUV(0.45, 0.9, 0.55, 1, 0);
            t.addVertexWithUV(x + 0.45, y + 0.5, z + 0.55, 1, 1);
            t.addVertexWithUV(x + 0.45, y + 0.5, z + 0.45, 0, 1);
            t.addVertexWithUV(0.45, 0.9, 0.45, 0, 0);
        }
        t.draw();
        glEnable(GL_CULL_FACE);
    }
}

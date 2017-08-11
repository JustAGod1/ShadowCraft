package com.justagod.shadowcraft.block.witherreplacer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yuri on 06.07.17.
 */
public class WitherReplacerRenderer extends TileEntitySpecialRenderer {

    private ResourceLocation crystal = new ResourceLocation("shadowcraft", "textures/blocks/raw.png");
    private float angle = 0f;

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float tick) {
        glPushMatrix();
        {
            glTranslated(x, y, z);
            if (((WitherReplacerEntity) entity).getCrystal() != null) {
                glPushMatrix();
                {
                    glTranslated(0.5, 2, 0.5);
                    glRotated(angle, 0, 1, 0);
                    drawCrystal();
                }
                glPopMatrix();
            }
        }
        glPopMatrix();
        angle += 0.3;
    }

    private void drawCrystal() {
        Tessellator t = Tessellator.instance;

        glDisable(GL_CULL_FACE);
        glPushMatrix();
        {
            t.startDrawing(GL_TRIANGLES);
            {
                bindTexture(crystal);

                t.addVertexWithUV(-0.5, 0, -0.5, 0, 1);
                t.addVertexWithUV(0, 1, 0, 0.5, 0);
                t.addVertexWithUV(0.5, 0, -0.5, 1, 1);

                t.addVertexWithUV(0.5, 0, -0.5, 0, 1);
                t.addVertexWithUV(0, 1, 0, 0.5, 0);
                t.addVertexWithUV(0.5, 0, 0.5, 1, 1);

                t.addVertexWithUV(0.5, 0, 0.5, 1, 1);
                t.addVertexWithUV(0, 1, 0, 0.5, 0);
                t.addVertexWithUV(-0.5, 0, 0.5, 0, 1);

                t.addVertexWithUV(-0.5, 0, 0.5, 1, 1);
                t.addVertexWithUV(0, 1, 0, 0.5, 0);
                t.addVertexWithUV(-0.5, 0, -0.5, 0, 1);


                t.addVertexWithUV(0.5, 0, -0.5, 1, 1);
                t.addVertexWithUV(0, -1, 0, 0.5, 0);
                t.addVertexWithUV(-0.5, 0, -0.5, 0, 1);

                t.addVertexWithUV(0.5, 0, 0.5, 1, 1);
                t.addVertexWithUV(0, -1, 0, 0.5, 0);
                t.addVertexWithUV(0.5, 0, -0.5, 0, 1);


                t.addVertexWithUV(-0.5, 0, 0.5, 1, 1);
                t.addVertexWithUV(0, -1, 0, 0.5, 0);
                t.addVertexWithUV(0.5, 0, 0.5, 0, 1);

                t.addVertexWithUV(-0.5, 0, -0.5, 0, 1);
                t.addVertexWithUV(0, -1, 0, 0.5, 0);
                t.addVertexWithUV(-0.5, 0, 0.5, 1, 1);

            }
            t.draw();
        }
        glPopMatrix();
        glEnable(GL_CULL_FACE);
    }


}

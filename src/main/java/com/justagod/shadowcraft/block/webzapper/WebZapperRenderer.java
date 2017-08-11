package com.justagod.shadowcraft.block.webzapper;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yuri on 17.07.17.
 */
public class WebZapperRenderer extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity rawEntity, double x, double y, double z, float partialTick) {
        WebZapperEntity entity = (WebZapperEntity) rawEntity;

        if (!entity.isLinked()) return;

        float lx = entity.getLinkPos().getX();
        float ly = entity.getLinkPos().getY();
        float lz = entity.getLinkPos().getZ();

        float angle = entity.getTicksCount() / 10 * 11.25f;

        if (entity.isAlpha()) {
            glPushMatrix();
            {
                glDisable(GL_CULL_FACE);
                glTranslated(x + 0.5, y + 0.5, z + 0.5);

                Tessellator t = Tessellator.instance;


                if (Double.compare(entity.xCoord, lx) == 0) {
                    glRotated(angle, 0, 0, 1);


                    t.startDrawingQuads();
                    {
                        t.setColorOpaque_F(256, 0, 0);

                        t.addVertex(-0.05, -0.05, 0);
                        t.addVertex(-0.05, -0.05, lz - entity.zCoord);
                        t.addVertex(-0.05, 0.05, lz - entity.zCoord);
                        t.addVertex(-0.05, 0.05, 0);

                        t.addVertex(-0.05, 0.05, 0);
                        t.addVertex(-0.05, 0.05, lz - entity.zCoord);
                        t.addVertex(0.05, 0.05, lz - entity.zCoord);
                        t.addVertex(0.05, 0.05, 0);

                        t.addVertex(0.05, -0.05, 0);
                        t.addVertex(0.05, -0.05, lz - entity.zCoord);
                        t.addVertex(0.05, 0.05, lz - entity.zCoord);
                        t.addVertex(0.05, 0.05, 0);

                        t.addVertex(-0.05, -0.05, 0);
                        t.addVertex(-0.05, -0.05, lz - entity.zCoord);
                        t.addVertex(0.05, -0.05, lz - entity.zCoord);
                        t.addVertex(0.05, -0.05, 0);

                    }
                    t.draw();
                } else {
                    glRotated(angle, 1, 0, 0);


                    t.startDrawingQuads();
                    {
                        t.setColorOpaque_F(256, 0, 0);

                        t.addVertex(0, -0.05, -0.05);
                        t.addVertex(lx - entity.xCoord, -0.05, -0.05);
                        t.addVertex(lx - entity.xCoord, 0.05, -0.05);
                        t.addVertex(0, 0.05, -0.05);

                        t.addVertex(0, 0.05, -0.05);
                        t.addVertex(lx - entity.xCoord, 0.05, -0.05);
                        t.addVertex(lx - entity.xCoord, 0.05, 0.05);
                        t.addVertex(0, 0.05, 0.05);

                        t.addVertex(0, -0.05, 0.05);
                        t.addVertex(lx - entity.xCoord, -0.05, 0.05);
                        t.addVertex(lx - entity.xCoord, 0.05, 0.05);
                        t.addVertex(0, 0.05, 0.05);

                        t.addVertex(0, -0.05, -0.05);
                        t.addVertex(lx - entity.xCoord, -0.05, -0.05);
                        t.addVertex(lx - entity.xCoord, -0.05, 0.05);
                        t.addVertex(0, -0.05, 0.05);
                    }
                    t.draw();
                }



                glEnable(GL_CULL_FACE);
            }
            glPopMatrix();
        }

    }
}

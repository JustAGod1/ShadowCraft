package com.justagod.shadowcraft.Blocks.StringsCreator;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

/**
 * Драсьте, сделано Yuri
 * В 21:11
 */
public class StringsCreatorRender extends TileEntitySpecialRenderer {

    private ResourceLocation spiderTexture = new ResourceLocation("shadowcraft", "textures/items/artificial_spider.png");
    private ResourceLocation stringTexture = new ResourceLocation("shadowcraft", "textures/items/string.png");

    @Override
    public void renderTileEntityAt(TileEntity rawEntity, double x, double y, double z, float partialTick) {
        StringsCreatorEntity entity = (StringsCreatorEntity) rawEntity;

        glPushMatrix();
        {
            glTranslated(x + 0.5, y + 0.5, z + 0.5);

            glPushMatrix();
            {
                glRotated(entity.getGraphicTicks() + partialTick + 45, 0, 0, 1);
                {
                    glPushMatrix();
                    {
                        glTranslated(0, 0.4, 0.);
                        drawSpider();
                    }
                    glPopMatrix();

                    glPushMatrix();
                    {
                        glTranslated(0, -0.4, 0);
                        drawSpider();
                    }
                    glPopMatrix();

                    if (entity.hasSpiderAt(0)) {
                        glPushMatrix();
                        {
                            glTranslated(-0.4, 0, 0);
                            glRotated(90, 0, 0, 1);
                            drawSpider();
                        }
                        glPopMatrix();
                    }

                    if (entity.hasSpiderAt(1)) {
                        glPushMatrix();
                        {
                            glTranslated(0.4, 0, 0);
                            glRotated(90, 0, 0, 1);
                            drawSpider();
                        }
                        glPopMatrix();
                    }


                }

            }
            glPopMatrix();


            glPushMatrix();
            {


                glRotated(entity.getGraphicTicks() + partialTick, 1, 0, 0);

                {
                    if (entity.hasSpiderAt(2)) {
                        glPushMatrix();
                        {
                            glTranslated(0, 0.4, 0);
                            drawSpider();
                        }
                        glPopMatrix();
                    }

                    if (entity.hasSpiderAt(3)) {
                        glPushMatrix();
                        {
                            glTranslated(0, -0.4, 0);
                            drawSpider();
                        }
                        glPopMatrix();
                    }

                    if (entity.hasSpiderAt(4)) {
                        glPushMatrix();
                        {
                            glTranslated(0, 0, -0.4);
                            glRotated(90, 1, 0, 0);
                            drawSpider();
                        }
                        glPopMatrix();
                    }

                    if (entity.hasSpiderAt(5)) {
                        glPushMatrix();
                        {
                            glTranslated(0, 0, 0.4);
                            glRotated(90, 1, 0, 0);
                            drawSpider();
                        }
                        glPopMatrix();
                    }
                }
            }
            glPopMatrix();

            glPushMatrix();
            {
                glRotated(entity.getGraphicTicks() + partialTick, 0, 1, 0);
                glRotated(-90, 1, 0, 0);
                {
                    if (entity.hasString()) {
                        drawString();
                    }
                }
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    private void drawSpider() {
        Tessellator t = Tessellator.instance;

        bindTexture(spiderTexture);

        glDisable(GL_CULL_FACE);
        t.startDrawingQuads();
        {
            for (double y = -0.0125; y <= 0.0125f; y += 0.00001f) {
                t.setNormal(0, 1, 0);
                t.addVertexWithUV(-0.1, y, -0.1, 0, 1);
                t.addVertexWithUV(0.1, y, -0.1, 1, 1);
                t.addVertexWithUV(0.1, y, 0.1, 1, 0);
                t.addVertexWithUV(-0.1, y, 0.1, 0, 0);
            }


        }
        t.draw();
        glEnable(GL_CULL_FACE);
    }

    private void drawString() {
        Tessellator t = Tessellator.instance;

        bindTexture(stringTexture);

        glDisable(GL_CULL_FACE);
        t.startDrawingQuads();
        {
            for (double y = -0.0125; y <= 0.0125f; y += 0.00001f) {
                t.setNormal(0, 1, 0);
                t.addVertexWithUV(-0.2, y, -0.2, 0, 1);
                t.addVertexWithUV(0.2, y, -0.2, 1, 1);
                t.addVertexWithUV(0.2, y, 0.2, 1, 0);
                t.addVertexWithUV(-0.2, y, 0.2, 0, 0);
            }


        }
        t.draw();

        glEnable(GL_CULL_FACE);
    }
}

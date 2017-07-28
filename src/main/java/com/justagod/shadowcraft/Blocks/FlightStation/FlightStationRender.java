package com.justagod.shadowcraft.Blocks.FlightStation;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yuri on 26.07.17.
 */
public class FlightStationRender extends TileEntitySpecialRenderer {

    private ResourceLocation crossbeams = new ResourceLocation("shadowcraft", "textures/blocks/flight_station/crossbeam.png");
    private ResourceLocation edge = new ResourceLocation("shadowcraft", "textures/blocks/flight_station/edge.png");
    private ResourceLocation core = new ResourceLocation("shadowcraft", "textures/blocks/flight_station/core.png");
    private ResourceLocation glass = new ResourceLocation("shadowcraft", "textures/blocks/flight_station/glass.png");
    private ResourceLocation glass_edge = new ResourceLocation("shadowcraft", "textures/blocks/flight_station/glass_edge.png");
    private double angle = 0;

    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float partialTick) {
        glPushMatrix();
        {
            glTranslated(x, y, z);

            angle += 0.1;

            drawCrossbeams();
            drawEdges();
            drawCore((((FlightStationEntity) entity).getTicks() + partialTick) * 2.0f);
            drawGlasses();
        }
        glPopMatrix();
    }

    private void drawCore(float angle) {
        glPushMatrix();
        {
            glTranslated(0.5, 0.5, 0.5);
            glRotated(angle, 0, 1, 0);



            Tessellator t = Tessellator.instance;
            bindTexture(core);

            t.startDrawingQuads();
            {
                t.setNormal(0, 1, 0);
                t.addVertexWithUV(-0.15, 0.15, -0.15, 0, 1);
                t.addVertexWithUV(-0.15, 0.15, 0.15, 0, 0);
                t.addVertexWithUV(0.15, 0.15, 0.15, 1, 0);
                t.addVertexWithUV(0.15, 0.15, -0.15, 1, 1);

                t.setNormal(0, 0, -1);
                t.addVertexWithUV(-0.15, -0.15, -0.15, 0, 1);
                t.addVertexWithUV(-0.15, 0.15, -0.15, 0, 0);
                t.addVertexWithUV(0.15, 0.15, -0.15, 1, 0);
                t.addVertexWithUV(0.15, -0.15, -0.15, 1, 1);

                t.setNormal(0, -1, 0);
                t.addVertexWithUV(-0.15, -0.15, -0.15, 0, 1);
                t.addVertexWithUV(0.15, -0.15, -0.15, 1, 1);
                t.addVertexWithUV(0.15, -0.15, 0.15, 1, 0);
                t.addVertexWithUV(-0.15, -0.15, 0.15, 0, 0);

                t.setNormal(0, 0, 1);
                t.addVertexWithUV(-0.15, -0.15, 0.15, 0, 1);
                t.addVertexWithUV(0.15, -0.15, 0.15, 1, 1);
                t.addVertexWithUV(0.15, 0.15, 0.15, 1, 0);
                t.addVertexWithUV(-0.15, 0.15, 0.15, 0, 0);

                t.setNormal(1, 0, 0);
                t.addVertexWithUV(0.15, -0.15, -0.15, 0, 1);
                t.addVertexWithUV(0.15, 0.15, -0.15, 0, 0);
                t.addVertexWithUV(0.15, 0.15, 0.15, 1, 0);
                t.addVertexWithUV(0.15, -0.15, 0.15, 1, 1);

                t.setNormal(-1, 0, 0);
                t.addVertexWithUV(-0.15, -0.15, -0.15, 0, 1);
                t.addVertexWithUV(-0.15, -0.15, 0.15, 1, 1);
                t.addVertexWithUV(-0.15, 0.15, 0.15, 1, 0);
                t.addVertexWithUV(-0.15, 0.15, -0.15, 0, 0);

            }
            t.draw();
        }
        glPopMatrix();
    }

    private void drawGlasses() {
        glDisable(GL_CULL_FACE);
        glPushMatrix();
        drawGlass();
        glPopMatrix();

        glPushMatrix();
        glTranslated(1, 0, 0);
        glRotated(-90, 0, 1, 0);
        drawGlass();
        glPopMatrix();

        glPushMatrix();
        glTranslated(1, 0, 1);
        glRotated(180, 0, 1, 0);
        drawGlass();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0, 0, 1);
        glRotated(90, 0, 1, 0);
        drawGlass();
        glPopMatrix();

        glPushMatrix();
        glTranslated(1, 0.99, 0);
        glRotated(90, 0, 0, 1);
        drawGlass();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0, 0.009, 0);
        glRotated(-90, 0, 0, 1);
        drawGlass();
        glPopMatrix();

        glEnable(GL_CULL_FACE);
    }

    private void drawGlass() {
        glPushMatrix();
        {
            Tessellator t = Tessellator.instance;

            bindTexture(glass);

            t.startDrawingQuads();
            {
                t.setNormal(0, 0, -1);

                t.addVertexWithUV(0.001, 0.001, 0.001, 0, 1);
                t.addVertexWithUV(0.001, 0.001, 0.999, 1, 1);
                t.addVertexWithUV(0.001, 0.999, 0.999, 1, 0);
                t.addVertexWithUV(0.001, 0.999, 0.001, 0, 0);
            }
            t.draw();

            bindTexture(glass_edge);

            t.startDrawingQuads();
            {
                t.setNormal(0, 0, -1);

                t.addVertexWithUV(0.001, 0.001, 0.001, 0, 1);
                t.addVertexWithUV(0.001, 0.001, 0.999, 1, 1);
                t.addVertexWithUV(0.001, 0.999, 0.999, 1, 0);
                t.addVertexWithUV(0.001, 0.999, 0.001, 0, 0);
            }
            t.draw();
        }
        glPopMatrix();
    }

    private double getCoreTranslation() {
        // TODO: 26.07.17 Исправить левитацию ядра в станции полетов
        double tmp = (angle / 100) % 0.4;

        double result;
        if (tmp > 0.2) {
            result =  0.2 - (tmp - 0.3);
        } else {
            result = tmp - 0.1;
        }

        return result;

    }

    private void drawCrossbeams() {
        glPushMatrix();
        glTranslated(0.5, 0.109, 0.2);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();

        glTranslated(0.2, 0.11, 0.5);
        glRotated(90, 0, 1, 0);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0.8, 0.11, 0.5);
        glRotated(90, 0, 1, 0);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0.5, 0.109, 0.8);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0, 0.69, 0);

        glPushMatrix();
        glTranslated(0.5, 0.2001, 0.2);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();

        glTranslated(0.2, 0.2, 0.5);
        glRotated(90, 0, 1, 0);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0.8, 0.2, 0.5);
        glRotated(90, 0, 1, 0);
        drawCrossbeam();
        glPopMatrix();

        glPushMatrix();
        glTranslated(0.5, 0.2001, 0.8);
        drawCrossbeam();
        glPopMatrix();
        glPopMatrix();
    }

    private void drawCrossbeam() {
        glPushMatrix();
        bindTexture(crossbeams);

        Tessellator t = Tessellator.instance;

        t.startDrawingQuads();
        {
            t.setNormal(0, 0, -1);
            t.addVertexWithUV(-0.4, -0.1, -0.1, 0, 1);
            t.addVertexWithUV(-0.4, 0.1, -0.1, 0, 0);
            t.addVertexWithUV(0.4, 0.1, -0.1, 1, 0);
            t.addVertexWithUV(0.4, -0.1, -0.1, 1, 1);

            t.setNormal(0, 1, 0);
            t.addVertexWithUV(-0.4, 0.1, -0.1, 0, 1);
            t.addVertexWithUV(-0.4, 0.1, 0.1, 0, 0);
            t.addVertexWithUV(0.4, 0.1, 0.1, 1, 0);
            t.addVertexWithUV(0.4, 0.1, -0.1, 1, 1);

            t.setNormal(0, 0, 1);
            t.addVertexWithUV(-0.4, -0.1, 0.1, 0, 1);
            t.addVertexWithUV(0.4, -0.1, 0.1, 0, 0);
            t.addVertexWithUV(0.4, 0.1, 0.1, 1, 0);
            t.addVertexWithUV(-0.4, 0.1, 0.1, 1, 1);


            t.setNormal(0, -1, 0);
            t.addVertexWithUV(-0.4, -0.1, -0.1, 0, 1);
            t.addVertexWithUV(0.4, -0.1, -0.1, 1, 1);
            t.addVertexWithUV(0.4, -0.1, 0.1, 1, 0);
            t.addVertexWithUV(-0.4, -0.1, 0.1, 0, 0);

        }
        t.draw();
        glPopMatrix();
    }

    private void drawEdges() {
        glDisable(GL_CULL_FACE);
        glPushMatrix();
        {
            glPushMatrix();
            {
                glTranslated(0, 1, 0);
                drawEdge();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslated(1, 1, 0);
                glRotated(90, 0, -1, 0);
                drawEdge();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslated(1, 1, 1);
                glRotated(180, 0, 1, 0);
                drawEdge();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslated(0, 1, 1);
                glRotated(90, 0, 1, 0);
                drawEdge();
            }
            glPopMatrix();

            //----------------------


            glTranslated(0, 0.001, 0);
            glPushMatrix();
            {
                glTranslated(0, 0, 0);
                glRotated(180, 1, 0, 0);
                glRotated(90, 0, 1, 0);
                drawEdge();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslated(1, 0, 0);
                glRotated(180, 1, 0, 0);
                glRotated(180, 0, 1, 0);
                drawEdge();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslated(1, 0, 1);
                glRotated(180, 1, 0, 0);
                glRotated(-90, 0, 1, 0);
                drawEdge();
            }
            glPopMatrix();

            glPushMatrix();
            {
                glTranslated(0, 0, 1);
                glRotated(180, 1, 0, 0);
                drawEdge();
            }
            glPopMatrix();
        }
        glPopMatrix();
        glEnable(GL_CULL_FACE);
    }

    private void drawEdge() {
        Tessellator t = Tessellator.instance;

        bindTexture(edge);

        t.startDrawing(GL_TRIANGLES);
        {
            t.setNormal(0, 0, -1);
            t.addVertexWithUV(0, 0, 0, 0, 0);
            t.addVertexWithUV(0.45, 0, 0, 1, 0);
            t.addVertexWithUV(0, -0.45, 0, 0, 1);

            t.setNormal(-1, 0, 0);
            t.addVertexWithUV(0, -0.45, 0, 0, 1);
            t.addVertexWithUV(0, 0, 0.45, 1, 0);
            t.addVertexWithUV(0, 0, 0, 0, 0);

            t.setNormal(0, 1, 0);
            t.addVertexWithUV(0, 0, 0.45, 0, 1);
            t.addVertexWithUV(0.45, 0, 0, 1, 0);
            t.addVertexWithUV(0, 0, 0, 0, 0);
        }
        t.draw();
    }
}

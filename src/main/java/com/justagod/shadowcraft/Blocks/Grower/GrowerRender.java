package com.justagod.shadowcraft.Blocks.Grower;

import com.justagod.shadowcraft.Utils.RenderUtility;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.*;

/**
 * Драсьте, сделано Yuri
 * В 21:52
 */
public class GrowerRender extends TileEntitySpecialRenderer {

    private ResourceLocation white = new ResourceLocation("shadowcraft", "textures/blocks/white_texture.png");

    @Override
    public void renderTileEntityAt(TileEntity rawEntity, double x, double y, double z, float partialTick) {
        glPushMatrix();
        {
            GrowerTile entity = (GrowerTile) rawEntity;
            glTranslated(x, y, z);

            drawPads();
            drawProgressParts((entity.getGraphicTick() / 20) % 9);

            glPushMatrix();
            {
                glTranslated(0.5001, 0.005001, 0.5001);

                bindTexture(white);
                glColor3d(0, 0, 0);
                RenderUtility.drawCube(0.49, 0.005, 0.49, true);
            }
            glPopMatrix();
        }
        glPopMatrix();
    }

    private void drawPads() {
        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.16);

            drawPad();
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.16);

            drawPad();
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.5, 0.02501, 0.3);

            drawPad();
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.49, 0.02501, 0.84);

            drawPad();
        }
        glPopMatrix();
    }

    private void drawPad() {
        Tessellator t = Tessellator.instance;

        bindTexture(white);

        RenderUtility.drawCube(0.15, 0.025, 0.15, true);

        glPushMatrix();
        {
            glTranslated(0, 0.0325, -0.13 + 0.025);
            RenderUtility.drawCube(0.13, 0.0075, 0.025, true);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0, 0.0325, 0.13 - 0.025);
            RenderUtility.drawCube(0.13, 0.0075, 0.025, true);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.13 - 0.025, 0.0325, 0);
            RenderUtility.drawCube(0.025, 0.0075, 0.09, true);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(-0.13 + 0.025, 0.0325, 0);
            RenderUtility.drawCube(0.025, 0.0075, 0.09, true);
        }
        glPopMatrix();
    }

    private void drawProgressParts(int workingIndex) {
        glPushMatrix();
        {
            glTranslated(0.5, 0.02501, 0.5);

            drawBigProgressPart(workingIndex == 0 || workingIndex == 1);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.5, 0.02501, 0.55);

            drawBigProgressPart(workingIndex == 2 || workingIndex == 3);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.5, 0.02501, 0.6);

            drawBigProgressPart(workingIndex == 4 || workingIndex == 5);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.5, 0.02501, 0.65);

            drawBigProgressPart(workingIndex == 6 || workingIndex == 7);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.35);

            drawProgressPart(workingIndex == 1);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.35);

            drawProgressPart(workingIndex == 1);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.4);

            drawProgressPart(workingIndex == 2);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.4);

            drawProgressPart(workingIndex == 2);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.45);

            drawProgressPart(workingIndex == 3);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.45);

            drawProgressPart(workingIndex == 3);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.53);
            glRotated(-45, 0, 1, 0);

            drawProgressPart(workingIndex == 4);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.53);
            glRotated(45, 0, 1, 0);

            drawProgressPart(workingIndex == 4);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.24, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 5);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.76, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 5);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.29, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 6);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.71, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 6);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.34, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 7);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.66, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 7);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.39, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 8);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.61, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 8);
        }
        glPopMatrix();
    }

    private void drawProgressPart(boolean isWorking) {
        bindTexture(white);

        if (isWorking) {
            glColor3d(1, 0, 0);
        } else {
            glColor3d(1, 1, 1);
        }
        RenderUtility.drawCube(0.05, 0.01, 0.02, true);
    }

    private void drawBigProgressPart(boolean isWorking) {
        bindTexture(white);

        if (isWorking) {
            glColor3d(1, 0, 0);
        } else {
            glColor3d(1, 1, 1);
        }
        RenderUtility.drawCube(0.065, 0.01, 0.02, true);
    }
}

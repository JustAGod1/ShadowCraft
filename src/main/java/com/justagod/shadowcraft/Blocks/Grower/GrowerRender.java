package com.justagod.shadowcraft.Blocks.Grower;

import com.justagod.shadowcraft.Utils.RenderUtility;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

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

            int meta = entity.blockMetadata;

            glRotated(90 * meta, 0, 1, 0);
            if (meta == 1) {
                glTranslated(-1, 0, 0);
            } else if (meta == 2) {
                glTranslated(-1, 0, -1);
            } else if (meta == 3) {
                glTranslated(0, 0, -1);
            }

            drawPads(entity);
            drawProgressParts(entity.isWorking()?(entity.getGraphicTick() / 20) % 9:-1, entity.getFirstSecondary() != null, entity.getSecondSecondary() != null);

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

    private void drawPads(GrowerTile entity) {
        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.16);

            drawPad();

            ItemStack stack = entity.getFirstSecondary();
            if (stack != null) {
                double scale = entity.getFirstScale() + 0.01;
                glTranslated(0, 0.2, 0);


                glRotated(entity.getGraphicTick(), 0, 1, 0);

                RenderUtility.drawItem(stack.getItem(), 0.15 * scale, 0.01 * scale);
            }
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.16);

            drawPad();

            ItemStack stack = entity.getSecondSecondary();
            if (stack != null) {
                double scale = entity.getSecondScale() + 0.01;
                glTranslated(0, 0.2, 0);


                glRotated(entity.getGraphicTick(), 0, 1, 0);

                RenderUtility.drawItem(stack.getItem(), 0.15 * scale, 0.01 * scale);

            }
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.5, 0.02501, 0.3);

            drawPad();

            ItemStack stack = entity.getMain();
            if (stack != null) {
                double scale = entity.getMainScale() + 0.01;
                glTranslated(0, 0.2, 0);


                glRotated(entity.getGraphicTick(), 0, 1, 0);

                RenderUtility.drawItem(stack.getItem(), 0.15 * scale, 0.01 * scale);

            }
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.49, 0.02501, 0.84);

            drawPad();

            ItemStack stack = entity.getOutput();
            if (stack != null) {
                double scale = entity.getOutputScale() + 0.01;
                glTranslated(0, 0.2, 0);


                glRotated(entity.getGraphicTick(), 0, 1, 0);

                RenderUtility.drawItem(stack.getItem(), 0.15 * scale, 0.01 * scale);

            }
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

    private void drawProgressParts(int workingIndex, boolean lightFirst, boolean lightSecond) {
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

            drawProgressPart(workingIndex == 1 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.35);

            drawProgressPart(workingIndex == 1 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.4);

            drawProgressPart(workingIndex == 2 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.4);

            drawProgressPart(workingIndex == 2 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.45);

            drawProgressPart(workingIndex == 3 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.45);

            drawProgressPart(workingIndex == 3 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.16, 0.02501, 0.53);
            glRotated(-45, 0, 1, 0);

            drawProgressPart(workingIndex == 4 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.84, 0.02501, 0.53);
            glRotated(45, 0, 1, 0);

            drawProgressPart(workingIndex == 4 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.24, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 5 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.76, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 5 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.29, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 6 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.71, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 6 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.34, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 7 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.66, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 7 && lightSecond);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.39, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 8 && lightFirst);
        }
        glPopMatrix();

        glPushMatrix();
        {
            glTranslated(0.61, 0.02501, 0.53);
            glRotated(90, 0, 1, 0);

            drawProgressPart(workingIndex == 8 && lightSecond);
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
            glColor3b((byte) 220, (byte) 40, (byte) 90);

        } else {
            glColor3d(1, 1, 1);
        }
        RenderUtility.drawCube(0.065, 0.01, 0.02, true);
    }
}

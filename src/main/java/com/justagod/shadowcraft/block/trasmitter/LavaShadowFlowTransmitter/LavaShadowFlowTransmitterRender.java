package com.justagod.shadowcraft.block.trasmitter.LavaShadowFlowTransmitter;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Yuri on 09.07.17.
 */
public class LavaShadowFlowTransmitterRender extends TileEntitySpecialRenderer {
    @Override
    public void renderTileEntityAt(TileEntity rawEntity, double x, double y, double z, float partialTick) {
        LavaShadowFlowTransmitterEntity entity = (LavaShadowFlowTransmitterEntity) rawEntity;

        if (!entity.isLinked()) return;
        glPointSize(15);
        glPushMatrix();
        {
            Tessellator t = Tessellator.instance;

            t.startDrawing(GL_LINES);
            {
                t.addVertex(x + 0.5, y + 0.5, z + 0.5);
                t.addVertex(entity.getLinkPos().getX() + 0.5, entity.getLinkPos().getY() + 1, entity.getLinkPos().getZ() + 0.5);
            }
            t.draw();

        }
        glPopMatrix();
    }
}

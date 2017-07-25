package com.justagod.shadowcraft.Blocks.StringsCreator;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;

/**
 * Created by Yuri on 23.07.17.
 */
public class StringsCreatorGUI extends GuiContainer {

    private ResourceLocation scGuiTexture = new ResourceLocation("shadowcraft", "textures/gui/strings_creator.png");

    public StringsCreatorGUI(Container container) {
        super(container);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(scGuiTexture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 0, (int) (((StringsCreatorContainer) inventorySlots).getEntity().getProgress() * 24 + 1), 17);

        glPushMatrix();
        {
            //glRotated(180, 0, 0, 0);

            int i1 = (int) (((StringsCreatorContainer) inventorySlots).getEntity().getFoodScale() * 17);
            this.drawTexturedModalRect(k + 145, l + 66 + 12 - i1, 176, 35 - i1, 5, i1 + 1);
        }
        glPopMatrix();
    }
}

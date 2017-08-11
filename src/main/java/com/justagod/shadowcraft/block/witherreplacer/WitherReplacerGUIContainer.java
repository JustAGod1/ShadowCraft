package com.justagod.shadowcraft.block.witherreplacer;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

/**
 * Created by Yuri on 16.07.17.
 */
public class WitherReplacerGUIContainer extends GuiContainer {


    public WitherReplacerGUIContainer(Container container) {
        super(container);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        int i = (this.width) / 2;
        int j = (this.height) / 2;
        glPushMatrix();
        {
            mc.getTextureManager().bindTexture(new ResourceLocation("shadowcraft", "textures/gui/wither_replacer.png"));
            drawTexturedModalRect(i - 116, j - 100, 0, 0, 216, 75);
            fontRendererObj.drawString(I18n.format("container.wither_replacer"), i - 108, j - 97, 100);
            WitherReplacerEntity entity = ((WitherReplacerContainer) inventorySlots).getEntity();

            if (entity.isHaveCrystal()) {
                fontRendererObj.drawString(I18n.format("container.wither_replacer.crystal", I18n.format(entity.getCrystal().getItem().getUnlocalizedName() + ".name")), i - 108, j - 84, 100);
                float flows = entity.getShadowFlowsCount();
                int requiredFlows = entity.getRequiredFlows();
                fontRendererObj.drawString((I18n.format("container.wither_replacer.shadows", (entity.isWorking()?"§2":"§4") + (flows + "/" + requiredFlows))), i - 108, j - 70, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.caption", entity.getCaption()), i - 108, j - 56, 100);
                if (entity.getCurrentMaxDistance() != -1)
                    fontRendererObj.drawString(I18n.format("container.wither_replacer.status_working"), i - 108, j - 42, 100);
                else
                    fontRendererObj.drawString(I18n.format("container.wither_replacer.status_idle"), i - 108, j - 42, 100);

            } else {
                fontRendererObj.drawString(I18n.format("container.wither_replacer.havent_crystal"), i - 108, j - 84, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.shadows_doesnt_required"), i - 108, j - 70, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.caption", entity.getCaption()), i - 100, j - 56, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.status_idle"), i - 108, j - 42, 100);
            }
        }
        glPopMatrix();
    }








}

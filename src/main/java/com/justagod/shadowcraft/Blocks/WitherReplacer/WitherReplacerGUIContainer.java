package com.justagod.shadowcraft.Blocks.WitherReplacer;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.glBindTexture;
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
            drawTexturedModalRect(i - 108, j - 100, 0, 0, 216, 75);
            fontRendererObj.drawString(I18n.format("container.wither_replacer"), i - 100, j - 97, 100);
            WitherReplacerEntity entity = ((WitherReplacerContainer) inventorySlots).getEntity();

            if (entity.isHaveCrystal()) {
                fontRendererObj.drawString(I18n.format("container.wither_replacer.crystal", I18n.format(entity.getCrystal().getItem().getUnlocalizedName() + ".name")), i - 100, j - 85, 100);
                int flows = entity.getShadowFlowsCount();
                int requiredFlows = entity.getRequiredFlows();
                fontRendererObj.drawString((flows >= requiredFlows?"§2":"§4") + I18n.format("container.wither_replacer.shadows", (flows + "/" + requiredFlows)), i - 100, j - 72, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.caption", entity.getCaption()), i - 100, j - 58, 100);
                if (entity.getCurrentMaxDistance() != -1)
                    fontRendererObj.drawString(I18n.format("container.wither_replacer.status_working"), i - 100, j - 44, 100);
                else
                    fontRendererObj.drawString(I18n.format("container.wither_replacer.status_idle"), i - 100, j - 44, 100);

            } else {
                fontRendererObj.drawString(I18n.format("container.wither_replacer.havent_crystal"), i - 100, j - 85, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.shadows_doesnt_required"), i - 100, j - 72, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.caption", entity.getCaption()), i - 100, j - 58, 100);
                fontRendererObj.drawString(I18n.format("container.wither_replacer.status_idle"), i - 100, j - 44, 100);
            }
        }
        glPopMatrix();
    }








}

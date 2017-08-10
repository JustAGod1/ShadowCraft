package com.justagod.shadowcraft.Blocks.Grower;

import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import static org.lwjgl.opengl.GL11.glTranslated;

/**
 * Драсьте, сделано Yuri
 * В 20:09
 */
public class GrowerItemRender implements IItemRenderer {
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return item != null && item.getItem() == Item.getItemFromBlock(ShadowCraft.grower);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            glTranslated(0, 0.6, 0);
        }
        GrowerTile tile = new GrowerTile();
        tile.setMeta(0);
        TileEntityRendererDispatcher.instance.renderTileEntityAt(tile, 0, 0, 0, 0);
    }
}

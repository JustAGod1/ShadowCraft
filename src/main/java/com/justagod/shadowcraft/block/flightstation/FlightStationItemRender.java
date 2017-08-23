package com.justagod.shadowcraft.block.flightstation;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.client.renderer.texture.Stitcher;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

/**
 * Created by Yuri on 27.07.17.
 */
public class FlightStationItemRender implements IItemRenderer {
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return item != null && item.getItem() == Item.getItemFromBlock(ShadowCraft.flight_station_block);
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        TileEntityRendererDispatcher.instance.renderTileEntityAt(new FlightStationEntity(), 0, 0, 0, 0);
    }
}

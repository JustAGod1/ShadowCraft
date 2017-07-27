package com.justagod.shadowcraft.Blocks.PearlCreator;

import com.justagod.shadowcraft.Blocks.ShadowBlock;
import com.justagod.shadowcraft.Flows.FlowReceiver;
import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yuri on 25.07.17.
 */
public class PearlCreatorBlock extends FlowReceiver implements ITileEntityProvider{

    // TODO: 26.07.17 Доделать создатель эндер перлов
    public PearlCreatorBlock() {
        super(Material.clay);
    }

    @Override
    public boolean needDrawFlowEffects(World world, int x, int y, int z) {
        return false;
    }

    @Override
    public FlowReceiverEntity getFlowReceiverEntity(World world, int meta) {
        return new PearlCreatorEntity();
    }
}

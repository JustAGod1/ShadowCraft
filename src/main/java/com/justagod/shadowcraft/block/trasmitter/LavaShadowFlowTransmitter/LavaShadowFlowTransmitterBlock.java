package com.justagod.shadowcraft.block.trasmitter.LavaShadowFlowTransmitter;

import com.justagod.shadowcraft.misc.flow.FlowTransmitter;
import com.justagod.shadowcraft.misc.flow.FlowTransmitterEntity;
import com.justagod.shadowcraft.misc.flow.Linkable;
import com.justagod.shadowcraft.util.Vector3;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

import static com.justagod.shadowcraft.ShadowCraft.blocks;

/**
 * Created by Yuri on 09.07.17.
 */
public class LavaShadowFlowTransmitterBlock extends FlowTransmitter {

    public LavaShadowFlowTransmitterBlock() {
        super(Material.clay);
        setCreativeTab(blocks);
        setBlockTextureName("shadowcraft:lava_shadow_flow_transmitter");
        setBlockName("lava_shadow_flow_transmitter");
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public void onBlockLinked(Linkable linkable, World world, Vector3 linkPos, Vector3 blockPos) {
        LavaShadowFlowTransmitterEntity entity = (LavaShadowFlowTransmitterEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.setLinkBlock(linkable);
        entity.setLinkPos(linkPos);
    }

    @Override
    public void onLinkedBlockDestroyed(World world, Vector3 linkPos, Vector3 blockPos) {
        LavaShadowFlowTransmitterEntity entity = (LavaShadowFlowTransmitterEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.setLinkBlock(null);
        entity.setLinkPos(null);

    }

    @Override
    public void onBlockUnlinked(World world, Vector3 linkPos, Vector3 blockPos) {
        LavaShadowFlowTransmitterEntity entity = (LavaShadowFlowTransmitterEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.setLinkPos(null);
        entity.setLinkBlock(null);
    }


    @Override
    protected FlowTransmitterEntity getFlowTransmitterEntity(World world, int meta) {
        return new LavaShadowFlowTransmitterEntity();
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        LavaShadowFlowTransmitterEntity entity = (LavaShadowFlowTransmitterEntity) world.getTileEntity(x, y, z);

        if (entity.getLinkBlock() != null) {
            entity.getLinkBlock().onLinkedBlockDestroyed(world, new Vector3(x, y, z), entity.getLinkPos());
        }
    }
}

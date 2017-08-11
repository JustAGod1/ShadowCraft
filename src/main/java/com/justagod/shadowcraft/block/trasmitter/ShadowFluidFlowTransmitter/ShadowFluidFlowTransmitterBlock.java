package com.justagod.shadowcraft.block.trasmitter.ShadowFluidFlowTransmitter;

import com.justagod.shadowcraft.flow.FlowTransmitter;
import com.justagod.shadowcraft.flow.FlowTransmitterEntity;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Драсьте, сделано Yuri
 * В 15:43
 */
public class ShadowFluidFlowTransmitterBlock extends FlowTransmitter {

    private IIcon side;
    private IIcon top;
    private IIcon bottom;

    public ShadowFluidFlowTransmitterBlock() {
        super(Material.anvil);
        setBlockBounds(0, 0.6f, 0, 1, 1, 1);
        setBlockName("shadow_fluid_flow_transmitter");
    }

    @Override
    protected FlowTransmitterEntity getFlowTransmitterEntity(World world, int meta) {
        return new ShadowFluidFlowTransmitterTile();
    }

    @Override
    public boolean canPlaceBlockAt(World world, int fx, int y, int fz) {
        for (int x = fx - 2; x <= fx + 2; x++) {
            for (int z = fz - 2; z <= fz + 2; z++) {
                if (world.getBlock(x, y, z) == ShadowCraft.shadow_fluid_transmitter_block) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        top = register.registerIcon("shadowcraft:shadow_fluid_transmitter/top");
        bottom = register.registerIcon("shadowcraft:shadow_fluid_transmitter/bottom");
        side = register.registerIcon("shadowcraft:shadow_fluid_transmitter/side");
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == 1) return top;
        if (side == 0) return bottom;
        return this.side;
    }
}

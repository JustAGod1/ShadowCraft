package com.justagod.shadowcraft.Blocks.WebZapper;

import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import static com.justagod.shadowcraft.ShadowCraft.blocks;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperBlock extends Block implements ITileEntityProvider {

    private IIcon faceIcon;
    private IIcon sideIcon;

    public WebZapperBlock() {
        super(new WitherReplacerMaterial());
        setBlockName("web_zapper");
        setCreativeTab(blocks);
        setHarvestLevel("pickaxe", 2);
        setHardness(20);
        setResistance(2000);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new WebZapperEntity();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase livingBase, ItemStack stack) {
        int l = MathHelper.floor_double((double)(livingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        if (side == meta) return faceIcon;
        if ((meta == -1) && (side == 4)) return faceIcon;
        else return sideIcon;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        faceIcon = register.registerIcon("shadowcraft:web_zapper_face");
        sideIcon = register.registerIcon("shadowcraft:web_zapper");
    }
}

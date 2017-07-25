package com.justagod.shadowcraft.Blocks.WebZapper;

import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerMaterial;
import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.Flows.SingleLinkEntity;
import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Yuri on 09.07.17.
 */
public class WebZapperBlock extends Linkable implements ITileEntityProvider {

    private IIcon faceIcon;
    private IIcon sideIcon;

    public WebZapperBlock() {
        super(new WitherReplacerMaterial());
        setBlockName("web_zapper");
        setHarvestLevel("pickaxe", 2);
        setHardness(20);
        setResistance(2000);
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
        if ((meta == 0) && (side == 4)) return faceIcon;
        else return sideIcon;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        faceIcon = register.registerIcon("shadowcraft:web_zapper_face");
        sideIcon = register.registerIcon("shadowcraft:web_zapper");
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sideX, float sideY, float sideZ) {
        if (!world.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(String.valueOf(side)));
        }
        return true;
    }

    @Override
    public boolean isValidObjToBind(Linkable obj, Vector3 linkPos, Vector3 blockPos, World world) {
        if (!(obj instanceof WebZapperBlock)) return false;

        int x = (int) blockPos.getX();
        int y = (int) blockPos.getY();
        int z = (int) blockPos.getZ();

        int lX = (int) linkPos.getX();
        int lY = (int) linkPos.getY();
        int lZ = (int) linkPos.getZ();

        int meta = world.getBlockMetadata(x, y, z);

        if (y != lY) return false;

        if (x == lX && lZ - z <= 10 && lZ - z > 0 && meta == 3) {
            for (int i = z + 1; i < lZ; i++) {
                if (!world.getBlock(x, y, i).isReplaceable(world, x, y, i) && world.getBlock(x, y, i) != Blocks.web) {
                    return false;
                }
            }
            return true;
        }
        if (x == lX && z - lZ <= 10 && z - lZ > 0 && meta == 2) {
            for (int i = z - 1; i > lZ; i--) {
                if (!world.getBlock(x, y, i).isReplaceable(world, x, y, i) && world.getBlock(x, y, i) != Blocks.web) {
                    return false;
                }
            }
            return true;
        }
        if (z == lZ && x - lX <= 10 && x - lX > 0 && meta == 4) {
            for (int i = x - 1; i > lX; i--) {
                if (!world.getBlock(i, y, z).isReplaceable(world, i, y, z) && world.getBlock(i, y, z) != Blocks.web) {
                    return false;
                }
            }
            return true;
        }
        if (z == lZ && lX - x <= 10 && lX - x > 0 && meta == 5) {
            for (int i = x + 1; i < lX; i++) {
                if (!world.getBlock(i, y, z).isReplaceable(world, i, y, z) && world.getBlock(i, y, z) != Blocks.web) {
                    return false;
                }
            }
            return true;
        }

        return false;

    }

    @Override
    public void onBlockLinked(Linkable linkable, World world, Vector3 linkPos, Vector3 blockPos) {
        super.onBlockLinked(linkable, world, linkPos, blockPos);

        int x = (int) blockPos.getX();
        int y = (int) blockPos.getY();
        int z = (int) blockPos.getZ();

        int lX = (int) linkPos.getX();
        int lY = (int) linkPos.getY();
        int lZ = (int) linkPos.getZ();

        WebZapperEntity myEntity = (WebZapperEntity) world.getTileEntity(x, y, z);
        WebZapperEntity linkEntity = (WebZapperEntity) world.getTileEntity(lX, lY, lZ);


    }

    @Override
    protected SingleLinkEntity getLinkableEntity(World world, int meta) {
        return new WebZapperEntity();
    }
}

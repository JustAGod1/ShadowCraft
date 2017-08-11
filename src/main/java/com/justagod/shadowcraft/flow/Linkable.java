package com.justagod.shadowcraft.flow;


import com.justagod.shadowcraft.block.ShadowBlock;
import com.justagod.shadowcraft.util.Vector3;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Yuri on 08.07.17.
 */
public abstract class Linkable extends ShadowBlock implements ITileEntityProvider {

    public Linkable(Material material) {
        super(material);
    }

    public abstract boolean isValidObjToBind(Linkable obj, Vector3 linkPos, Vector3 blockPos, World world);

    public void onBlockLinked(Linkable linkable, World world, Vector3 linkPos, Vector3 blockPos) {
        LinkableEntity entity = (LinkableEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.onLinked(linkPos, linkable);
    }

    public void onLinkedBlockDestroyed(World world, Vector3 linkPos, Vector3 blockPos) {
        LinkableEntity entity = (LinkableEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.onUnlinked(linkPos);
    }

    public void onBlockUnlinked(World world, Vector3 linkPos, Vector3 blockPos) {
        LinkableEntity entity = (LinkableEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.onUnlinked(linkPos);
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        LinkableEntity entity = (LinkableEntity) world.getTileEntity(x, y, z);


        entity.onBlockDestroy();

    }

    @Override
    public final TileEntity createNewTileEntity(World world, int meta) {
        return getLinkableEntity(world, meta);
    }

    protected abstract LinkableEntity getLinkableEntity(World world, int meta);
}

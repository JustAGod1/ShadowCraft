package com.justagod.shadowcraft.Flows;


import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

/**
 * Created by Yuri on 08.07.17.
 */
public abstract class Linkable extends Block {

    public Linkable(Material material) {
        super(material);
    }

    public abstract boolean isValidObjToBind(Linkable obj);

    public abstract void onBlockLinked(Linkable linkable, World world, Vector3 linkPos, Vector3 blockPos);

    public abstract void onLinkedBlockDestroyed(World world, Vector3 linkPos, Vector3 blockPos);

    public abstract void onBlockUnlinked(World world, Vector3 linkPos, Vector3 blockPos);
}

package com.justagod.shadowcraft.Flows;

import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yuri on 18.07.17.
 */
public abstract class LinkableEntity extends TileEntity {

    public abstract void onLinked(Vector3 linkPos, Linkable linkBlock);

    public abstract void onUnlinked(Vector3 linkPos);

    public abstract void onBlockDestroy();
}

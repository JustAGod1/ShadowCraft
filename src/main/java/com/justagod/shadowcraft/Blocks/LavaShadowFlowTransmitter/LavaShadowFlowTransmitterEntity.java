package com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter;

import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Yuri on 09.07.17.
 */
public class LavaShadowFlowTransmitterEntity extends TileEntity {

    private Vector3 linkPos;
    private Linkable linkBlock;

    public Vector3 getLinkPos() {
        return linkPos;
    }

    public void setLinkPos(Vector3 linkPos) {
        this.linkPos = linkPos;
    }

    public Linkable getLinkBlock() {
        return linkBlock;
    }

    public void setLinkBlock(Linkable linkBlock) {
        this.linkBlock = linkBlock;
    }

    public boolean isHaveLink() {
        return linkPos != null;
    }
}

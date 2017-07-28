package com.justagod.shadowcraft.Blocks.WitherReplacer;

import com.justagod.shadowcraft.Flows.FlowReceiver;
import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import com.justagod.shadowcraft.Flows.FlowTransmitter;
import com.justagod.shadowcraft.Flows.Linkable;
import com.justagod.shadowcraft.Network.GUIHandler;
import com.justagod.shadowcraft.Network.PacketHandler;
import com.justagod.shadowcraft.Network.Packets.TellerToClientPacket;
import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.Items.ShadowCrystals.ShadowCrystal;
import com.justagod.shadowcraft.Utils.Vector3;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import static com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity.SERVER_INSTANCES;

/**
 * Created by Yuri on 06.07.17.
 */
public class WitherReplacerBlock extends FlowReceiver {

    private IIcon bottom;
    private IIcon top;
    private IIcon side;


    public WitherReplacerBlock() {
        super(new WitherReplacerMaterial());
        setCreativeTab(ShadowCraft.blocks);
        setBlockBounds(0, 0, 0, 1, 0.7f, 1);
        setBlockName("wither_replacer");
        setResistance(9000);
        setHardness(30);
        setHarvestLevel("pickaxe", 2);

    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_) {
        if (!world.isRemote) {
            PacketHandler.INSTANCE.sendToAll(new TellerToClientPacket(x, y, z));
        }
        return p_149660_9_;
    }

    @Override
    public boolean isToolEffective(String type, int metadata) {
        return type.equals("pickaxe");
    }

    @Override
    public void onBlockLinked(Linkable linkable, World world, Vector3 linkPos, Vector3 blockPos) {
        WitherReplacerEntity entity = (WitherReplacerEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.addTransmitter(linkPos, (FlowTransmitter) linkable);

    }

    @Override
    public void onLinkedBlockDestroyed(World world, Vector3 linkPos, Vector3 blockPos) {
        WitherReplacerEntity entity = (WitherReplacerEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.removeTransmitter(linkPos);
    }

    @Override
    public void onBlockUnlinked(World world, Vector3 linkPos, Vector3 blockPos) {
        WitherReplacerEntity entity = (WitherReplacerEntity) world.getTileEntity((int) blockPos.getX(), (int) blockPos.getY(), (int) blockPos.getZ());

        entity.removeTransmitter(linkPos);
    }

    @Override
    public boolean renderAsNormalBlock() {
        return true;
    }

    @Override
    public int getLightOpacity() {
        return 0;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        top = register.registerIcon("shadowcraft:bevel");
        bottom = register.registerIcon("shadowcraft:raw");
        side = register.registerIcon("shadowcraft:quarters");
    }


    @Override
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 4:
            case 5:
            case 2:
            case 3: {
                return this.side;
            }
            case 0: {
                return bottom;
            }

        }
        return top;
    }



    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        super.onBlockDestroyedByPlayer(world, x, y, z, meta);

        deleteEntity(x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
        WitherReplacerEntity entity = (WitherReplacerEntity) world.getTileEntity(x, y, z);

        if (entity.getCrystal() != null) {
            EntityItem entityItem = new EntityItem(world, x + 0.5, y + 0.7, z + 0.5,  entity.getCrystal());
            world.spawnEntityInWorld(entityItem);
        }
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) {
        super.onBlockPreDestroy(world, x, y, z, meta);

        deleteEntity(x, y, z);
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
        super.onBlockDestroyedByExplosion(world, x, y, z, explosion);

        deleteEntity(x, y, z);
    }

    private void deleteEntity(int x, int y, int z) {
        for (int i = 0; i < SERVER_INSTANCES.size(); i++) {
            WitherReplacerEntity entity = SERVER_INSTANCES.get(i);

            if ((entity.xCoord == x) && (entity.yCoord == y) && (entity.zCoord == z)) SERVER_INSTANCES.remove(i);
        }
    }



    @Override
    public synchronized boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float clickX, float clickY, float clickZ) {
        if (!world.isRemote) {
            System.out.println("ПКМ");

            WitherReplacerEntity entity = (WitherReplacerEntity) world.getTileEntity(x, y, z);
            if (entity == null) {
                player.addChatComponentMessage(new ChatComponentTranslation("msg.entityNullMsg.txt"));
            }
            if (side == 1) {

                if (entity.getCrystal() != null) {
                    if ((player.getCurrentEquippedItem() == null) && (player.isSneaking())) {
                        world.spawnEntityInWorld(new EntityItem(world, x, y + 1, z, entity.getCrystal()));
                        entity.setCrystal(null);
                    }
                    return true;
                }

                if (player.getCurrentEquippedItem() == null) return true;
                if (player.getCurrentEquippedItem().getItem() instanceof ShadowCrystal) {
                    entity.setCrystal(player.getCurrentEquippedItem().splitStack(1));
                }
            } else {
                player.openGui(ShadowCraft.instance, GUIHandler.WITHER_REPLACER_GUI, world, x, y, z);
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean needDrawFlowEffects(World world, int x, int y, int z) {
        return ((WitherReplacerEntity) world.getTileEntity(x, y, z)).getCrystal() != null;
    }

    @Override
    public FlowReceiverEntity getFlowReceiverEntity(World world, int meta) {
        return new WitherReplacerEntity();
    }
}

package com.justagod.shadowcraft.item.rechargeable;

import com.justagod.shadowcraft.item.ShadowWand;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.justagod.shadowcraft.item.rechargeable.RechargeHelper.updateItem;
import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 09.07.17.
 */
public class ShadowShears extends ItemShears {

    public ShadowShears() {
        super();
        setMaxDamage(647);
        setCreativeTab(items);
        setUnlocalizedName("shadow_shears");
        setTextureName("shadowcraft:shadow_shears");
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "shadow_shears_upgrade");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        list.add(I18n.format("sc.tooltip.shadow_shears"));
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        updateItem(entityItem);

        return false;
    }

    private class WandUpgrade extends ShadowWand.WandUpgrade {

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.shadow_shears");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowShears.this);
        }

        @Override
        public float getEfficientOn(ShadowWand.WandWrapper wrapper, Block block) {
            return block != Blocks.web && block.getMaterial() != Material.leaves ? (block == Blocks.wool ? 5.0F : 1) : 15.0F;
        }

        @Override
        public boolean onBlockDestroy(ShadowWand.WandWrapper wrapper, World world, Block block, int x, int y, int z, EntityLivingBase livingBase) {
            return !(block.getMaterial() != Material.leaves && block != Blocks.web && block != Blocks.tallgrass && block != Blocks.vine && block != Blocks.tripwire && !(block instanceof IShearable));
        }

        @Override
        public boolean onBlockStartBreak(ShadowWand.WandWrapper wandWrapper, int x, int y, int z, EntityPlayer player) {
            if (player.worldObj.isRemote)
            {
                return false;
            }
            Block block = player.worldObj.getBlock(x, y, z);
            if (block instanceof IShearable)
            {
                IShearable target = (IShearable)block;
                if (target.isShearable(new ItemStack(ShadowShears.this), player.worldObj, x, y, z))
                {
                    ArrayList<ItemStack> drops = target.onSheared(new ItemStack(ShadowShears.this), player.worldObj, x, y, z,
                            EnchantmentHelper.getEnchantmentLevel(Enchantment.fortune.effectId, wandWrapper.getWand()));
                    Random rand = new Random();

                    for(ItemStack stack : drops)
                    {
                        float f = 0.7F;
                        double d  = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d1 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        double d2 = (double)(rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
                        EntityItem entityitem = new EntityItem(player.worldObj, (double)x + d, (double)y + d1, (double)z + d2, stack);
                        entityitem.delayBeforeCanPickup = 10;
                        player.worldObj.spawnEntityInWorld(entityitem);
                    }

                    player.addStat(StatList.mineBlockStatArray[Block.getIdFromBlock(block)], 1);
                }
            }
            return false;
        }
    }


}

package com.justagod.shadowcraft.item.upgrade;

import com.justagod.shadowcraft.item.ShadowItem;
import com.justagod.shadowcraft.item.ShadowWand;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

import java.util.List;

/**
 * Драсьте, сделано Yuri
 * В 12:11
 */
public class PersonLockerUpgradeItem extends ShadowItem {

    public PersonLockerUpgradeItem() {
        super();
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "person_locker_upgrade");
        setUnlocalizedName("person_locker_upgrade");
        setTextureName("shadowcraft:person_locker_upgrade");
        setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();
        compound.setString("name", player.getDisplayName());

        return stack;

    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();

        String name = compound.getString("name");
        if (!StringUtils.isNullOrEmpty(name)) {
            list.add(I18n.format("sc.tooltip.person_locker_upgrade", name));
        }
    }

    private class WandUpgrade extends ShadowWand.WandUpgrade {

        private String playerName;

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("sc.tooltip.person_locker_upgrade", playerName);
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(PersonLockerUpgradeItem.this);
        }

        @Override
        public boolean isAplicableTo(ShadowWand.WandWrapper wand, ItemStack stack) {
            NBTTagCompound compound = stack.getTagCompound();
            if (compound == null) return false;
            if (StringUtils.isNullOrEmpty(compound.getString("name"))) return false;
            for (ShadowWand.WandUpgrade upgrade : wand.getUpgrades()) {
                if (upgrade instanceof WandUpgrade) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public void onUpdate(ShadowWand.WandWrapper stack, World world, Entity entity, int slot, boolean isEquipped) {
            if (entity instanceof EntityPlayer) {
                if (!((EntityPlayer) entity).getDisplayName().equals(playerName)) {
                    ((EntityPlayer) entity).attackEntityFrom(DamageSource.outOfWorld, 1f);
                }
            }
        }

        @Override
        public void onUplicate(ShadowWand.WandWrapper wrapper, ItemStack upgradeStack) {
            playerName = upgradeStack.getTagCompound().getString("name");
        }

        @Override
        public boolean needInstantiate() {
            return true;
        }

        @Override
        public void writeToNBT(NBTTagCompound compound) {
            compound.setString("name", playerName);
        }

        @Override
        public void readFromNBT(NBTTagCompound compound) {
            playerName = compound.getString("name");
        }

        @Override
        public ShadowWand.WandUpgrade newInstance(ShadowWand.WandWrapper wand) {
            return new WandUpgrade();
        }
    }
}

package com.justagod.shadowcraft.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.justagod.shadowcraft.misc.IWandable;
import com.justagod.shadowcraft.misc.flow.Linkable;
import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.util.CraftingUtility;
import com.justagod.shadowcraft.util.Vector3;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.*;

/**
 * Created by Yuri on 08.07.17.
 */
public class ShadowWand extends ShadowItem {

    private static final String IS_BLOCK_IN_MEMORY_TAG = "is_block_in_memory";
    private static final String BLOCK_X_TAG = "block_x";
    private static final String BLOCK_Y_TAG = "block_y";
    private static final String BLOCK_Z_TAG = "block_z";


    public ShadowWand() {
        super();
        setUnlocalizedName("shadow_wand");
        setTextureName("shadowcraft:shadow_wand");
    }

    public static WandWrapper getWrapper(ItemStack stack) {
        if (stack.getItem() == ShadowCraft.shadow_wand) {
            return new WandWrapper(stack);
        } else {
            throw new RuntimeException("Тыры пыры. Нельзя делать обертки теневого жезла из других предметов");
        }
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        WandWrapper wrapper = getWrapper(stack);

        wrapper.getDescription(list);
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        WandWrapper wrapper = getWrapper(stack);

        return wrapper.getToolClasses();
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase livingBase) {
        WandWrapper wrapper = getWrapper(stack);

        return wrapper.fireBlockDestroy(world, block, x, y, z, livingBase);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack stack, int x, int y, int z, EntityPlayer player) {
        WandWrapper wrapper = getWrapper(stack);

        return wrapper.fireBlockStartBreak(x, y, z, player);
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        getWrapper(stack).fireItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
        if (!world.isRemote) {

            Block block = world.getBlock(x, y, z);

            if ((block instanceof Linkable)) {

                if (!stack.hasTagCompound()) {
                    stack.setTagCompound(new NBTTagCompound());
                }
                NBTTagCompound compound = stack.getTagCompound();


                boolean isBlockInMemory = compound.getBoolean(IS_BLOCK_IN_MEMORY_TAG);

                if (isBlockInMemory) {
                    int previousX = compound.getInteger(BLOCK_X_TAG);
                    int previousY = compound.getInteger(BLOCK_Y_TAG);
                    int previousZ = compound.getInteger(BLOCK_Z_TAG);

                    Block previousBlock = world.getBlock(previousX, previousY, previousZ);
                    if (!(previousBlock instanceof Linkable)) {
                        player.addChatComponentMessage(new ChatComponentTranslation("msg.something_was_changed.txt"));

                        compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, false);

                        return true;
                    }

                    Linkable firstLink = (Linkable) block;
                    Linkable secondLink = (Linkable) previousBlock;

                    player.addChatComponentMessage(new ChatComponentTranslation("msg.second_block_selected.txt"));

                    if (firstLink.isValidObjToBind(secondLink, new Vector3(previousX, previousY, previousZ), new Vector3(x, y, z), world) && secondLink.isValidObjToBind(firstLink, new Vector3(x, y, z), new Vector3(previousX, previousY, previousZ), world)) {
                        firstLink.onBlockLinked(secondLink, world, new Vector3(previousX, previousY, previousZ), new Vector3(x, y, z));
                        secondLink.onBlockLinked(firstLink, world, new Vector3(x, y, z), new Vector3(previousX, previousY, previousZ));

                        compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, false);

                        player.addChatComponentMessage(new ChatComponentTranslation("msg.bind_completed.txt"));
                    } else {
                        compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, false);

                        player.addChatComponentMessage(new ChatComponentTranslation("msg.unable_to_create_link.txt"));
                    }

                    return true;
                }

                compound.setBoolean(IS_BLOCK_IN_MEMORY_TAG, true);
                compound.setInteger(BLOCK_X_TAG, x);
                compound.setInteger(BLOCK_Y_TAG, y);
                compound.setInteger(BLOCK_Z_TAG, z);

                player.addChatComponentMessage(new ChatComponentTranslation("msg.first_block_selected.txt"));

                return true;
            } else if (block instanceof IWandable) {
                ((IWandable) block).onWand(getWrapper(stack), world, block, x, y, z, world.getBlockMetadata(x, y, z));
            }
        }
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        getWrapper(stack).fireRightClick(stack, world, player);
        return stack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isEquipped) {
        WandWrapper wrapper = getWrapper(stack);

        wrapper.fireUpdate(stack, world, entity, slot, isEquipped);
    }

    @Override
    public int getHarvestLevel(ItemStack stack, String toolClass) {
        WandWrapper wrapper = getWrapper(stack);

        return wrapper.getHarvestLevel(toolClass);
    }

    @Override
    public float func_150893_a(ItemStack stack, Block block) {
        WandWrapper wrapper = getWrapper(stack);
        return wrapper.getMaxEfficient(block);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int metadata) {
        if (ForgeHooks.isToolEffective(itemstack, block, metadata)) {
            return ShadowCraft.SHADOW_MATERIAL.getEfficiencyOnProperMaterial();
        }
        return super.getDigSpeed(itemstack, block, metadata);
    }

    public static final class UpgradeRegistry {
        private static final Map<String, WandUpgrade> UPGRADE_MAP = new HashMap<String, WandUpgrade>();

        public static void registerUpgrade(WandUpgrade upgrade, String id) {
            UPGRADE_MAP.put(id, upgrade);
        }

        public static void unregisterUpgrade(WandUpgrade upgrade) {
            Iterator<Map.Entry<String, WandUpgrade>> iter = UPGRADE_MAP.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<String, WandUpgrade> entry = iter.next();

                if (entry.getValue() == upgrade) iter.remove();
            }
        }

        public static void unregisterUpgrade(String id) {
            UPGRADE_MAP.remove(id);
        }

        public static WandUpgrade getUpgrade(String id) {
            return UPGRADE_MAP.get(id);
        }

        public static String getUpgradeId(WandUpgrade upgrade) {
            Iterator<Map.Entry<String, WandUpgrade>> iter = UPGRADE_MAP.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry<String, WandUpgrade> entry = iter.next();

                if (entry.getValue().getClass().getName().equals(upgrade.getClass().getName())) return entry.getKey();
            }
            return null;
        }

        public static Map<String, WandUpgrade> getUpgradeMap() {
            return UPGRADE_MAP;
        }
    }

    public abstract static class WandUpgrade {

        public abstract String getDescription(WandWrapper wand);

        public void onUpdate(WandWrapper stack, World world, Entity entity, int slot, boolean isEquipped) {
        }

        public void onItemUse(WandWrapper stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        }

        public String getToolClass() {
            return null;
        }

        public ItemStack onItemRightClick(WandWrapper stack, World world, EntityPlayer player) {
            return stack.wand;
        }

        public abstract ItemStack getUpgradeItem();

        public boolean isAplicableTo(WandWrapper wand, ItemStack stack) {
            return true;
        }

        public void onUplicate(WandWrapper wrapper, ItemStack upgradeStack) {
        }

        public boolean needInstantiate() {
            return false;
        }

        public int getHarvestLevel(String toolClass) {
            return -1;
        }

        public float getEfficientOn(WandWrapper wrapper, Block block) {
            return 1;
        }

        public void writeToNBT(NBTTagCompound compound) {
        }

        public void readFromNBT(NBTTagCompound compound) {
        }

        public WandUpgrade newInstance(WandWrapper wand) {
            return null;
        }

        public boolean onBlockDestroy(WandWrapper wrapper, World world, Block block, int x, int y, int z, EntityLivingBase livingBase) {
            return false;
        }

        public boolean onBlockStartBreak(WandWrapper wandWrapper, int x, int y, int z, EntityPlayer player) {
            return false;
        }
    }

    public static class UpgradeRecipe implements IRecipe {

        private ItemStack getWand(InventoryCrafting inventoryCrafting) {
            return CraftingUtility.getStack(inventoryCrafting, ShadowCraft.shadow_wand);
        }

        private boolean hasWand(InventoryCrafting inventoryCrafting) {
            return getWand(inventoryCrafting) != null;
        }

        private boolean hasOnlyOneUpgrade(InventoryCrafting inventoryCrafting) {
            int count = 0;
            for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
                ItemStack stack = inventoryCrafting.getStackInSlot(i);

                if (stack != null) {


                    if (isUpgradeItem(stack)) count++;
                    else if (stack.getItem() != ShadowCraft.shadow_wand) return false;
                }
            }

            return count == 1;
        }

        private boolean hasOnlyOneWand(InventoryCrafting inventoryCrafting) {
            int count = 0;
            for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
                ItemStack stack = inventoryCrafting.getStackInSlot(i);

                if (stack != null) {
                    if (stack.getItem() == ShadowCraft.shadow_wand) count++;
                }

            }

            return count == 1;
        }

        private ItemStack getUpgradeItem(InventoryCrafting inventoryCrafting) {
            for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
                ItemStack stack = inventoryCrafting.getStackInSlot(i);

                if (stack != null) {


                    if (isUpgradeItem(stack)) return stack;
                }
            }
            return null;
        }

        private boolean isUpgradeItem(ItemStack stack) {
            for (Map.Entry<String, WandUpgrade> entry : UpgradeRegistry.UPGRADE_MAP.entrySet()) {
                if (entry.getValue().getUpgradeItem().getItem() == stack.getItem()) {
                    return true;
                }
            }
            return false;
        }

        private WandUpgrade getUpgradeFromItemStack(ItemStack stack) {
            for (Map.Entry<String, WandUpgrade> entry : UpgradeRegistry.UPGRADE_MAP.entrySet()) {
                if (entry.getValue().getUpgradeItem().getItem() == stack.getItem()) {
                    return entry.getValue();
                }
            }
            return null;
        }

        private boolean checkForUpgrade(InventoryCrafting inventoryCrafting) {
            if (hasOnlyOneUpgrade(inventoryCrafting) && hasOnlyOneWand(inventoryCrafting)) {
                ItemStack upgradeStack = getUpgradeItem(inventoryCrafting);
                WandUpgrade upgrade = getUpgradeFromItemStack(upgradeStack);
                ItemStack wand = getWand(inventoryCrafting);
                return upgrade.isAplicableTo(getWrapper(wand), upgradeStack);
            } else return false;
        }

        @Override
        public boolean matches(InventoryCrafting inventoryCrafting, World world) {

            return checkForUpgrade(inventoryCrafting);

        }

        @Override
        public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {

            ItemStack copyWand = getWand(inventoryCrafting).copy();


            if (copyWand.getItem() != ShadowCraft.shadow_wand) {
                throw new RuntimeException("Херня какая-то! Так быть не должно.");
            }

            WandWrapper wrapper = ShadowCraft.shadow_wand.getWrapper(copyWand);

            wrapper.addUpgrade(getUpgradeFromItemStack(getUpgradeItem(inventoryCrafting)), getUpgradeItem(inventoryCrafting));
            wrapper.saveData();

            return copyWand;

        }

        @Override
        public int getRecipeSize() {
            return 2;
        }

        @Override
        public ItemStack getRecipeOutput() {
            return null;
        }
    }

    public static class WandWrapper {
        private static final String UPGRADES_LIST_TAG = "upgrades";
        private static final String UPGRADE_ID_TAG = "id";
        private static final String UPGRADE_DATA_TAG = "data";
        private ItemStack wand;
        private List<WandUpgrade> upgrades;

        public WandWrapper(ItemStack wand) {
            this.wand = wand;

            if (!wand.hasTagCompound()) {
                NBTTagCompound compound = new NBTTagCompound();
                wand.setTagCompound(compound);
            }

            NBTTagCompound compound = wand.getTagCompound();

            if (compound.getTagList(UPGRADES_LIST_TAG, 10) == null) {
                NBTTagList nbtTagList = new NBTTagList();
                compound.setTag(UPGRADES_LIST_TAG, nbtTagList);
            }
        }

        public List<WandUpgrade> getUpgrades() {
            if (upgrades == null) {
                upgrades = loadUpgradesFromNBT();
            }
            return upgrades;
        }

        private List<WandUpgrade> loadUpgradesFromNBT() {
            try {

                NBTTagCompound compound = wand.getTagCompound();

                NBTTagList tagList = compound.getTagList(UPGRADES_LIST_TAG, 10);

                List<WandUpgrade> result = new ArrayList<WandUpgrade>();

                for (int i = 0; i < tagList.tagCount(); i++) {
                    NBTTagCompound upgrade = tagList.getCompoundTagAt(i);
                    String id = upgrade.getString(UPGRADE_ID_TAG);
                    WandUpgrade wandUpgrade = UpgradeRegistry.getUpgrade(id);

                    if (wandUpgrade == null) throw new RuntimeException("Cant find upgrade for id: " + id);

                    if (wandUpgrade.needInstantiate()) {
                        WandUpgrade newInstance = wandUpgrade.newInstance(getWrapper(wand));

                        newInstance.readFromNBT(upgrade.getCompoundTag(UPGRADE_DATA_TAG));

                        result.add(newInstance);
                    } else {
                        result.add(wandUpgrade);
                    }

                }
                return result;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void saveData() {
            try {

                NBTTagCompound compound = wand.getTagCompound();

                NBTTagList upgradesNBT = new NBTTagList();

                for (int i = 0; i < getUpgrades().size(); i++) {
                    NBTTagCompound upgradeNBT = new NBTTagCompound();

                    WandUpgrade upgrade = getUpgrades().get(i);
                    String id = UpgradeRegistry.getUpgradeId(upgrade);

                    if (StringUtils.isNullOrEmpty(id))
                        throw new RuntimeException("Cant find upgrade id for class: " + upgrade.getClass());

                    upgradeNBT.setString(UPGRADE_ID_TAG, id);
                    if (upgrade.needInstantiate()) {
                        NBTTagCompound data = new NBTTagCompound();

                        upgrade.writeToNBT(data);

                        upgradeNBT.setTag(UPGRADE_DATA_TAG, data);
                    }
                    upgradesNBT.appendTag(upgradeNBT);
                }

                compound.setTag(UPGRADES_LIST_TAG, upgradesNBT);


            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public void addUpgrade(WandUpgrade upgrade, ItemStack upgradeStack) {
            if (upgrade == null) {
                throw new RuntimeException("Апгрэйд равен null");
            }
            if (upgrade.needInstantiate()) {
                WandUpgrade newInstance = upgrade.newInstance(this);
                getUpgrades().add(newInstance);
                newInstance.onUplicate(this, upgradeStack);
            } else {
                getUpgrades().add(upgrade);
                upgrade.onUplicate(this, upgradeStack);
            }
            saveData();
        }

        public void removeUpgrade(WandUpgrade upgrade) {
            getUpgrades().remove(upgrade);
        }

        public void getDescription(List list) {
            list.add(I18n.format("shadowwand.upgrade"));
            for (WandUpgrade upgrade : getUpgrades()) {
                if (upgrade != null) {


                    list.add(" - " + upgrade.getDescription(this));
                }
            }
        }

        public void fireUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isEquipped) {
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                upgrade.onUpdate(this, world, entity, slot, isEquipped);
            }
            saveData();
        }

        public void fireItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                upgrade.onItemUse(this, player, world, x, y, z, side, hitX, hitY, hitZ);
            }
            saveData();
        }

        public void fireRightClick(ItemStack stack, World world, EntityPlayer player) {
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                upgrade.onItemRightClick(this, world, player);
            }
            saveData();
        }

        public Set<String> getToolClasses() {
            Builder<String> builder = ImmutableSet.builder();

            for (WandUpgrade upgrade : getUpgrades()) {
                if (!StringUtils.isNullOrEmpty(upgrade.getToolClass())) {
                    builder.add(upgrade.getToolClass());
                }
            }
            return builder.build();
        }

        public int getHarvestLevel(String toolClass) {
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                int level = upgrade.getHarvestLevel(toolClass);
                if (level != -1) {
                    return level;
                }
            }
            return -1;
        }

        public float getMaxEfficient(Block block) {
            float max = Float.MIN_VALUE;
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                float efficient = upgrade.getEfficientOn(this, block);
                if (efficient > max) max = efficient;
            }
            return max;
        }

        public boolean fireBlockDestroy(World world, Block block, int x, int y, int z, EntityLivingBase livingBase) {
            boolean result = false;
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                if (upgrade.onBlockDestroy(this, world, block, x, y, z, livingBase)) {
                    result = true;
                }
            }
            return result;
        }

        public boolean fireBlockStartBreak(int x, int y, int z, EntityPlayer player) {
            boolean result = false;
            for (int i = 0; i < getUpgrades().size(); i++) {
                WandUpgrade upgrade = getUpgrades().get(i);
                if (upgrade.onBlockStartBreak(this, x, y, z, player)) {
                    result = true;
                }
            }
            return result;
        }

        public ItemStack getWand() {
            return wand;
        }
    }
}

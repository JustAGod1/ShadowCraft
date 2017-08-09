package com.justagod.shadowcraft.Blocks.Grower;

import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.Utils.MapUtility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.util.mapped.MappedHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Драсьте, сделано Yuri
 * В 14:57
 */
public class GrowerTile extends FlowReceiverEntity implements ISidedInventory {

    private static final Item GROW_ACCELERATOR = ShadowCraft.shadow_core;

    private boolean open = false;
    private int ticks = 0;
    private GrowRecipe currentRecipe;
    private int graphicTick;
    private ItemStack[] inventory = new ItemStack[4];

    @Override
    public void updateEntity() {
        graphicTick++;
    }

    public int getGraphicTick() {
        return graphicTick;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[] {0, 1, 2, 3};
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        if (slot == 0) {
            return GrowRecipeRegister.isInput(stack.getItem());
        }
        if (slot == 1 || slot == 2) {
            return stack.getItem() == GROW_ACCELERATOR;
        }
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 3;
    }

    @Override
    public int getSizeInventory() {
        return 4;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index < 0 || index >= this.getSizeInventory())
            return null;
        return this.inventory[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (this.getStackInSlot(index).stackSize <= count) {
                itemstack = this.getStackInSlot(index);
                this.setInventorySlotContents(index, null);
                this.markDirty();
                return itemstack;
            } else {
                itemstack = this.getStackInSlot(index).splitStack(count);

                if (this.getStackInSlot(index).stackSize <= 0) {
                    this.setInventorySlotContents(index, null);
                } else {
                    //Just to show that changes happened
                    this.setInventorySlotContents(index, this.getStackInSlot(index));
                }

                this.markDirty();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack stack = this.getStackInSlot(index);
        this.setInventorySlotContents(index, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index < 0 || index >= this.getSizeInventory())
            return;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
            stack.stackSize = this.getInventoryStackLimit();

        if (stack != null && stack.stackSize == 0)
            stack = null;

        this.inventory[index] = stack;
        this.markDirty();
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot == 0) {
            return GrowRecipeRegister.isInput(stack.getItem());
        }
        if (slot == 1 || slot == 2) {
            return stack.getItem() == GROW_ACCELERATOR;
        }
        return false;
    }

    public static class GrowRecipeRegister {
        private static final Map<String, GrowRecipe> RECIPES = new HashMap<String, GrowRecipe>();

        public static void registerRecipe(GrowRecipe recipe, String id) {
            if (getRecipe(id) != null) {
                System.err.println("Попытка зарегистрировать рецепт с занятым id");
                return;
            }
            RECIPES.put(id, recipe);

        }

        public static boolean isInput(final Item item) {
            return MapUtility.anyMatch(RECIPES, new MapUtility.Matcher<Map.Entry<String, GrowRecipe>>() {
                @Override
                public boolean match(Map.Entry<String, GrowRecipe> arg) {
                    return arg.getValue().getInput() == item;
                }
            });
        }

        public static GrowRecipe getRecipeByInput(final Item item) {
            return MapUtility.getSomeFromValue(RECIPES, new MapUtility.Tester<GrowRecipe, GrowRecipe>() {
                @Override
                public GrowRecipe test(GrowRecipe arg) {
                    return arg.getInput() == item?arg:null;
                }
            });
        }

        public static String getRecipeId(GrowRecipe recipe) {
            return MapUtility.getKeyByValue(RECIPES, recipe);
        }

        public static GrowRecipe getRecipe(String id) {
            return MapUtility.getValueByKey(RECIPES, id);
        }
    }

    public static class GrowRecipe {
        private Item input;
        private Item output;
        private int time;

        public GrowRecipe(Item input, Item output, int time) {
            this.input = input;
            this.output = output;
            this.time = time;
        }

        public Item getInput() {
            return input;
        }

        public Item getOutput() {
            return output;
        }

        public int getTime() {
            return time;
        }
    }
}

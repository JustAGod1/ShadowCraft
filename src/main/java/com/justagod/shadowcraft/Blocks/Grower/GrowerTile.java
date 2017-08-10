package com.justagod.shadowcraft.Blocks.Grower;

import com.justagod.shadowcraft.Flows.FlowReceiverEntity;
import com.justagod.shadowcraft.ShadowCraft;
import com.justagod.shadowcraft.Utils.MapUtility;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;
import java.util.Map;

/**
 * Драсьте, сделано Yuri
 * В 14:57
 */
public class GrowerTile extends FlowReceiverEntity implements ISidedInventory {

    public static final Item GROW_ACCELERATOR = ShadowCraft.shadow_core;
    public static final int ACCELERATOR_TIME = 600;

    private int ticksLeft = 0;
    private int firstLeft = 0;
    private int secondLeft = 0;
    private GrowRecipe currentRecipe;
    private int graphicTick;
    private ItemStack[] inventory = new ItemStack[getSizeInventory()];

    public boolean tryPutItem(ItemStack stack) {
        if (getMain() == null) {
            if (GrowRecipeRegister.isInput(stack.getItem()) && getOutput() == null) {
                setInventorySlotContents(0, new ItemStack(stack.getItem(), 1));

                GrowRecipe recipe = GrowRecipeRegister.getRecipeByInput(stack.getItem());
                setInventorySlotContents(3, new ItemStack(recipe.getOutput()));
                currentRecipe = recipe;
                ticksLeft = recipe.getTime();
                markEntity();
                return true;
            }
        } else if (getFirstSecondary() == null) {
            if (stack.getItem() == GROW_ACCELERATOR) {
                setInventorySlotContents(1, new ItemStack(GROW_ACCELERATOR, 1));
                firstLeft = ACCELERATOR_TIME;
                markEntity();
                return true;
            }
        } else if (getSecondSecondary() == null) {
            if (stack.getItem() == GROW_ACCELERATOR) {
                setInventorySlotContents(2, new ItemStack(GROW_ACCELERATOR, 1));
                secondLeft = ACCELERATOR_TIME;
                markEntity();
                return true;
            }
        }
        return false;
    }

    private void markEntity() {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        markDirty();
    }

    public double getFirstScale() {
        return (double) firstLeft / ACCELERATOR_TIME;
    }

    public double getSecondScale() {
        return (double) secondLeft / ACCELERATOR_TIME;
    }

    public double getMainScale() {
        if (currentRecipe != null) {
            return (double) ticksLeft / currentRecipe.getTime();
        } else return 0;
    }

    public double getOutputScale() {
        if (currentRecipe != null) {
            return (currentRecipe.getTime() - (double) ticksLeft) / currentRecipe.getTime();
        } else return 1;
    }

    public ItemStack getMain() {
        return getStackInSlot(0);
    }

    public ItemStack getFirstSecondary() {
        return getStackInSlot(1);
    }

    public ItemStack getSecondSecondary() {
        return getStackInSlot(2);
    }

    public ItemStack getOutput() {
        return getStackInSlot(3);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
            graphicTick++;

        if (!worldObj.isRemote && isWorking()) {
            if (currentRecipe != null) {
                if (ticksLeft > 0) {
                    ticksLeft--;
                    if (firstLeft > 0) ticksLeft--;
                    if (secondLeft > 0) ticksLeft--;
                } else {
                    setInventorySlotContents(0, null);
                    //setInventorySlotContents(3, new ItemStack(currentRecipe.getOutput()));
                    currentRecipe = null;
                    markEntity();
                }

                if (firstLeft > 0) {
                    firstLeft--;
                } else {
                    setInventorySlotContents(1, null);
                }

                if (secondLeft > 0) {
                    secondLeft--;
                } else {
                    setInventorySlotContents(2, null);
                }
                markEntity();
            }
        }
    }

    public int getGraphicTick() {
        return graphicTick;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[]{0, 1, 2, 3};
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
        return slot == 3 && ticksLeft <= 0;
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
        if (stack != null) {
            if (index == 1) {
                firstLeft = ACCELERATOR_TIME;
            }
            if (index == 2) {
                secondLeft = ACCELERATOR_TIME;
            }
            if (index == 0) {

                GrowRecipe recipe = GrowRecipeRegister.getRecipeByInput(stack.getItem());

                if (recipe != null) {
                    currentRecipe = recipe;
                    ticksLeft = recipe.getTime();
                    setInventorySlotContents(3, new ItemStack(recipe.getOutput()));
                }
            }
        }
        this.markDirty();
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    public boolean isWorking() {
        return getShadowFlowsCount() >= 10;
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

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        compound.setTag("Items", nbttaglist);
        compound.setInteger("ticks_left", ticksLeft);
        compound.setInteger("first_left", firstLeft);
        compound.setInteger("second_left", secondLeft);

        if (currentRecipe != null) {
            compound.setBoolean("has_recipe", true);
            compound.setString("recipe_id", GrowRecipeRegister.getRecipeId(currentRecipe));
        } else {
            compound.setBoolean("has_recipe", false);
        }

    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.inventory = new ItemStack[this.getSizeInventory()];


        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.inventory.length) {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        ticksLeft = compound.getInteger("ticks_left");
        firstLeft = compound.getInteger("first_left");
        secondLeft = compound.getInteger("second_left");

        if (compound.getBoolean("has_recipe")) {
            String id = compound.getString("recipe_id");
            GrowRecipe recipe = GrowRecipeRegister.getRecipe(id);
            if (recipe == null) {
                throw new RuntimeException("Не получается найти рецепт по id: " + id);
            }
            currentRecipe = recipe;
        } else {
            currentRecipe = null;
        }

    }

    public ItemStack getResult() {
        if (currentRecipe == null) return getOutput();
        else return null;
    }

    public int getMeta() {
        return blockMetadata;//worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
    }

    public void setMeta(int meta) {
        this.blockMetadata = meta;
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
                    return arg.getInput() == item ? arg : null;
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

package com.justagod.shadowcraft.util;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Драсьте, сделано Yuri
 * В 20:57
 */
public class CraftingUtility {

    public static boolean hasItem(InventoryCrafting inventoryCrafting, Item item) {
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stack = inventoryCrafting.getStackInSlot(i);
            if (stack != null) {


                if (stack.getItem() == item) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasItem(InventoryCrafting inventoryCrafting, ItemStack stack) {
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stack1 = inventoryCrafting.getStackInSlot(i);
            if (stack1.equals(stack)) {
                return true;
            }
        }
        return false;
    }

    public static ItemStack getStack(InventoryCrafting inventoryCrafting, Item item) {
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stack = inventoryCrafting.getStackInSlot(i);
            if (stack != null) {


                if (stack.getItem() == item) {
                    return inventoryCrafting.getStackInSlot(i);
                }
            }
        }
        return null;
    }
}

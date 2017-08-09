package com.justagod.shadowcraft.Blocks.StringsCreator;

import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Yuri on 23.07.17.
 */
public class StringsCreatorContainer extends Container {

    private StringsCreatorEntity entity;
    private IInventory playerInv;

    public StringsCreatorContainer(final StringsCreatorEntity entity, IInventory playerInv) {
        this.entity = entity;
        this.playerInv = playerInv;

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
        }

        addSlotToContainer(new Slot(entity, 0, 116, 35) {
            @Override
            public boolean isItemValid(ItemStack slot) {
                return false;
            }

        });
        addSlotToContainer(new Slot(entity, 7, 152, 61) {

            @Override
            public boolean isItemValid(ItemStack stack) {
                return entity.isItemValidForSlot(7, stack);
            }
        });

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                addSlotToContainer(new Slot(entity,i * 3 + j + 1, 19 + j * 18, 25 + i * 18) {
                    @Override
                    public boolean isItemValid(ItemStack stack) {
                        return stack.getItem() == ShadowCraft.artificial_spider;
                    }
                });
            }
        }


    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int fromSlot) {
        ItemStack previous = null;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();



            if (current.stackSize == 0)
                slot.putStack((ItemStack) null);
            else
                slot.onSlotChanged();

            if (current.stackSize == previous.stackSize)
                return null;
            slot.onPickupFromSlot(entityPlayer, current);
        }
        return previous;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return entity.isUseableByPlayer(entityPlayer);
    }

    public StringsCreatorEntity getEntity() {
        return entity;
    }
}

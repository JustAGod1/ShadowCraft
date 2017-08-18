package com.justagod.shadowcraft.item.upgrade;

import com.justagod.shadowcraft.item.ShadowItem;
import com.justagod.shadowcraft.item.ShadowWand;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

public class UpgradePlaceholder extends ShadowItem {

    public static final String PLACEHOLDER_ID = "placeholder_upgrade";

    public UpgradePlaceholder() {
        super();
        setUnlocalizedName("upgrade_placeholder");
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), PLACEHOLDER_ID);

    }

    private class WandUpgrade extends ShadowWand.WandUpgrade {

        @Override
        public String getDescription(ShadowWand.WandWrapper wand) {
            return I18n.format("shadowwand.upgrade.upgrade_placeholder");
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(UpgradePlaceholder.this);
        }
    }
}

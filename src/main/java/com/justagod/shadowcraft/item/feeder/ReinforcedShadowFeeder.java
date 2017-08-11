package com.justagod.shadowcraft.item.feeder;

import com.justagod.shadowcraft.item.ShadowWand;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.item.ItemStack;

import static com.justagod.shadowcraft.ShadowCraft.items;

/**
 * Created by Yuri on 11.07.17.
 */
public class ReinforcedShadowFeeder extends ShadowFeeder {

    public ReinforcedShadowFeeder() {
        setCreativeTab(items);
        setUnlocalizedName("reinforced_shadow_feeder");
        setTextureName("shadowcraft:reinforced_shadow_feeder");
        setMaxDamage(200);
        setMaxStackSize(1);
        ShadowWand.UpgradeRegistry.registerUpgrade(new WandUpgrade(), "reinforced_shadow_feeder_upgrade");
    }

    public class WandUpgrade extends ShadowFeeder.WandUpgrade {

        public WandUpgrade() {
            durability = 200;
        }

        @Override
        public ShadowWand.WandUpgrade newInstance(ShadowWand.WandWrapper wand) {
            return new WandUpgrade();
        }

        @Override
        public ItemStack getUpgradeItem() {
            return new ItemStack(ShadowCraft.reinforced_shadow_feeder);
        }
    }
}

package com.justagod.shadowcraft;

import com.justagod.shadowcraft.Blocks.ChargePads.LavaChargePad;
import com.justagod.shadowcraft.Blocks.FloatingBlock.FloatingBlock;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterRender;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperBlock;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperItemBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.CaptionLabelRenderer;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerRenderer;
import com.justagod.shadowcraft.CraftingItems.ShadowCore;
import com.justagod.shadowcraft.Items.AutoFeeders.ReinforcedShadowFeeder;
import com.justagod.shadowcraft.Items.AutoFeeders.ShadowFeeder;
import com.justagod.shadowcraft.Items.RechargeableItems.*;
import com.justagod.shadowcraft.Items.ShadowWand;
import com.justagod.shadowcraft.Items.ShadowCrystals.BudgetaryShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowCrystals.MediumShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowCrystals.StrongShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowCrystals.WeekShadowCrystal;
import com.justagod.shadowcraft.Network.GUIHandler;
import com.justagod.shadowcraft.Tabs.ShadowBlocksTab;
import com.justagod.shadowcraft.Tabs.ShadowItemsTab;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import static cpw.mods.fml.common.registry.GameRegistry.addRecipe;

@Mod(modid = ShadowCraft.MODID, version = ShadowCraft.VERSION)
public class ShadowCraft {
    public static final String MODID = "shadowcraft";
    public static final String VERSION = "1.0";
    public static final ToolMaterial SHADOW_MATERIAL = EnumHelper.addToolMaterial("SHADOW_MATERIAL", 3, 2897, 20, 5, 22);
    public static final ShadowBlocksTab blocks = new ShadowBlocksTab();
    public static final ShadowItemsTab items = new ShadowItemsTab();
    public static final ShadowCore shadowCore = new ShadowCore();
    public static final WitherReplacerBlock witherReplacer = new WitherReplacerBlock();
    public static final LavaShadowFlowTransmitterBlock lavaShadowFlowTransmitterBlock = new LavaShadowFlowTransmitterBlock();
    public static final FloatingBlock floatingBlock = new FloatingBlock();
    public static final WebZapperBlock webZapper = new WebZapperBlock();
    public static final LavaChargePad lavaChargePad = new LavaChargePad();
    public static final CaptionLabelRenderer captionLabelRenderer = new CaptionLabelRenderer();
    public static final BudgetaryShadowCrystal budgetaryShadowCrystal = new BudgetaryShadowCrystal();
    public static final WeekShadowCrystal weekShadowCrystal = new WeekShadowCrystal();
    public static final MediumShadowCrystal mediumShadowCrystal = new MediumShadowCrystal();
    public static final StrongShadowCrystal strongShadowCrystal = new StrongShadowCrystal();
    public static final ShadowWand shadowWand = new ShadowWand();
    public static final ShadowShears shadowShears = new ShadowShears();
    public static final ShadowPickaxe shadowPickaxe = new ShadowPickaxe();
    public static final ShadowAxe shadowAxe = new ShadowAxe();
    public static final ShadowShovel shadowShovel = new ShadowShovel();
    public static final ShadowHoe shadowHoe = new ShadowHoe();
    public static final ShadowFeeder shadowFeeder = new ShadowFeeder();
    public static final ReinforcedShadowFeeder reinforcedShadowFeeder = new ReinforcedShadowFeeder();
    public static ShadowCraft instance;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        instance = this;

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());

        GameRegistry.registerBlock(witherReplacer, "wither_replacer");
        GameRegistry.registerTileEntity(WitherReplacerEntity.class, "shadowcraft:shadowcraft:wither_replacer");
        ClientRegistry.bindTileEntitySpecialRenderer(WitherReplacerEntity.class, new WitherReplacerRenderer());

        GameRegistry.registerBlock(lavaShadowFlowTransmitterBlock, "lava_shadow_flow_transmitter_block");
        GameRegistry.registerTileEntity(LavaShadowFlowTransmitterEntity.class, "shadowcraft:lava_shadow_flow_transmitter");
        ClientRegistry.bindTileEntitySpecialRenderer(LavaShadowFlowTransmitterEntity.class, new LavaShadowFlowTransmitterRender());

        GameRegistry.registerBlock(webZapper, WebZapperItemBlock.class, "web_zapper");
        GameRegistry.registerTileEntity(WebZapperEntity.class, "shadowcraft:web_zapper");

        GameRegistry.registerBlock(lavaChargePad, "lava_charge_pad");

        GameRegistry.registerBlock(floatingBlock, "floating_block");

        GameRegistry.registerItem(budgetaryShadowCrystal, "budgetary_shadow_crystal");
        GameRegistry.registerItem(weekShadowCrystal, "week_shadow_crystal");
        GameRegistry.registerItem(mediumShadowCrystal, "medium_shadow_crystal");
        GameRegistry.registerItem(strongShadowCrystal, "strong_shadow_crystal");

        GameRegistry.registerItem(shadowCore, "shadow_core");

        GameRegistry.registerItem(shadowWand, "shadow_wand");
        GameRegistry.registerItem(shadowShears, "shadow_shears");
        GameRegistry.registerItem(shadowPickaxe, "shadow_pickaxe");
        GameRegistry.registerItem(shadowAxe, "shadow_axe");
        GameRegistry.registerItem(shadowShovel, "shadow_shovel");
        GameRegistry.registerItem(shadowHoe, "shadow_hoe");
        GameRegistry.registerItem(shadowFeeder, "shadow_feeder");
        GameRegistry.registerItem(reinforcedShadowFeeder, "reinforced_shadow_feeder");

        registerRecipes();

    }

    private void registerRecipes() {
        addRecipe(new ItemStack(witherReplacer), "O#O", "###", "#E#", 'O', ShadowCraft.shadowCore, '#', Blocks.obsidian, 'E', Items.ender_pearl);
        addRecipe(new ItemStack(shadowCore), "#O#", "OEO", "#O#", '#', Items.ender_pearl, 'O', Blocks.obsidian, 'E', Items.ender_eye);
        addRecipe(new ItemStack(budgetaryShadowCrystal), "#O#", "OGO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'G', Blocks.glowstone);
        addRecipe(new ItemStack(weekShadowCrystal), "#O#", "OEO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'E', shadowCore);
        addRecipe(new ItemStack(mediumShadowCrystal), " Q ", "COC", " Q ", 'Q', Blocks.quartz_block, 'C', weekShadowCrystal, 'O', shadowCore);
        addRecipe(new ItemStack(strongShadowCrystal), " Q ", "COC", " Q ", 'Q', shadowCore, 'C', mediumShadowCrystal, 'O', Items.nether_star);
        addRecipe(new ItemStack(shadowWand), "  Q", " R ", "R  ", 'R', Items.blaze_rod, 'Q', shadowCore);
        addRecipe(new ItemStack(shadowShears), "C C", " S ", " C ", 'C', shadowCore, 'S', Items.shears);
        addRecipe(new ItemStack(webZapper), "OSO", "SCS", "OSO", 'O', Blocks.obsidian, 'S', shadowShears, 'C', shadowCore);
        addRecipe(new ItemStack(floatingBlock), "STS", "TBT", "STS", 'S', Items.string, 'T', Blocks.stone, 'B', Items.water_bucket);
    }
}

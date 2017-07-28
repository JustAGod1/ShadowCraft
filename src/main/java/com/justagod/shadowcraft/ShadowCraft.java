package com.justagod.shadowcraft;

import com.justagod.shadowcraft.Blocks.AdminShadowFlowTransmitter.AdminShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.Blocks.AdminShadowFlowTransmitter.AdminShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.ChargePads.LavaChargePad;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationBlock;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.Blocks.FloatingBlock.FloatingBlock;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.Blocks.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.StringsCreator.StringsCreatorBlock;
import com.justagod.shadowcraft.Blocks.StringsCreator.StringsCreatorEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperBlock;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperItemBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Items.Absorbents.AbsorbedLight;
import com.justagod.shadowcraft.Items.Absorbents.AbsorbedShadow;
import com.justagod.shadowcraft.Items.Absorbents.LightAbsorbent;
import com.justagod.shadowcraft.Items.Absorbents.ShadowAbsorbent;
import com.justagod.shadowcraft.Items.AutoFeeders.AdvancedShadowFeeder;
import com.justagod.shadowcraft.Items.AutoFeeders.ReinforcedShadowFeeder;
import com.justagod.shadowcraft.Items.AutoFeeders.ShadowFeeder;
import com.justagod.shadowcraft.Items.CraftingItems.*;
import com.justagod.shadowcraft.Items.RechargeableItems.*;
import com.justagod.shadowcraft.Items.ShadowCrystals.BudgetaryShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowCrystals.MediumShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowCrystals.StrongShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowCrystals.WeekShadowCrystal;
import com.justagod.shadowcraft.Items.ShadowWand;
import com.justagod.shadowcraft.Items.SpidersFood;
import com.justagod.shadowcraft.Network.ClientProxy;
import com.justagod.shadowcraft.Network.CommonProxy;
import com.justagod.shadowcraft.Network.GUIHandler;
import com.justagod.shadowcraft.Tabs.ShadowBlocksTab;
import com.justagod.shadowcraft.Tabs.ShadowItemsTab;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

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
    public static final ArtificialSpider artificialSpider = new ArtificialSpider();
    public static final StringsCreatorBlock stringsCreatorBlock = new StringsCreatorBlock();
    public static final SpidersFood spidersFood = new SpidersFood();
    public static final LightAbsorbent lightAbsorbent = new LightAbsorbent();
    public static final ShadowAbsorbent shadowAbsorbent = new ShadowAbsorbent();
    public static final AbsorbedLight absorbedLight = new AbsorbedLight();
    public static final AbsorbedShadow absorbedShadow = new AbsorbedShadow();
    public static final FlightStationBlock flightStationBlock = new FlightStationBlock();
    public static final AdminShadowFlowTransmitterBlock adminShadowFlowTransmitterBlock = new AdminShadowFlowTransmitterBlock();
    public static final FlightStationCore flightStationCore = new FlightStationCore();
    public static final LightFilter lightFilter = new LightFilter();
    public static final ShadowFilter shadowFilter = new ShadowFilter();
    public static final AdvancedShadowFeeder advancedShadowFeeder = new AdvancedShadowFeeder();
    @SidedProxy(clientSide = "com.justagod.shadowcraft.Network.ClientProxy", serverSide = "com.justagod.shadowcraft.Network.CommonProxy")
    public static CommonProxy commonProxy;
    public static ShadowCraft instance;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        instance = this;

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());


        GameRegistry.registerBlock(witherReplacer, "wither_replacer");
        GameRegistry.registerTileEntity(WitherReplacerEntity.class, "shadowcraft:shadowcraft:wither_replacer");


        GameRegistry.registerBlock(lavaShadowFlowTransmitterBlock, "lava_shadow_flow_transmitter_block");
        GameRegistry.registerTileEntity(LavaShadowFlowTransmitterEntity.class, "shadowcraft:lava_shadow_flow_transmitter");

        GameRegistry.registerBlock(webZapper, WebZapperItemBlock.class, "web_zapper");
        GameRegistry.registerTileEntity(WebZapperEntity.class, "shadowcraft:web_zapper");

        GameRegistry.registerBlock(flightStationBlock, "flight_station");
        GameRegistry.registerTileEntity(FlightStationEntity.class, "shadowcraft:flight_station");

        GameRegistry.registerBlock(stringsCreatorBlock, "strings_creator_block");
        GameRegistry.registerTileEntity(StringsCreatorEntity.class, "shadowcraft:string_creator");

        GameRegistry.registerBlock(adminShadowFlowTransmitterBlock, "admin_shadow_flow_transmitter_block");
        GameRegistry.registerTileEntity(AdminShadowFlowTransmitterEntity.class, "shadowcraft:admin_shadow_flow_transmitter");

        //GameRegistry.registerBlock(lavaChargePad, "lava_charge_pad");

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
        GameRegistry.registerItem(artificialSpider, "artificial_spider");
        GameRegistry.registerItem(spidersFood, "spiders_food");
        GameRegistry.registerItem(lightAbsorbent, "light_absorbent");
        GameRegistry.registerItem(shadowAbsorbent, "shadow_absorbent");
        GameRegistry.registerItem(absorbedLight, "absorbed_light");
        GameRegistry.registerItem(absorbedShadow, "absorbed_shadow");
        GameRegistry.registerItem(lightFilter, "light_filter");
        GameRegistry.registerItem(shadowFilter, "shadow_filter");
        GameRegistry.registerItem(flightStationCore, "flight_station_core");
        GameRegistry.registerItem(advancedShadowFeeder, "advanced_shadow_feeder");


        if (event.getSide() == Side.CLIENT) {

            new ClientProxy().registerrenders();
        }
            MinecraftForge.EVENT_BUS.register(new SCEventHandler());


        registerRecipes();

    }

    private void registerRecipes() {
        addRecipe(new ItemStack(witherReplacer), " S ", "O#O", "#E#", 'O', ShadowCraft.shadowCore, '#', Blocks.obsidian, 'E', Items.ender_pearl, 'S', absorbedShadow);
        addRecipe(new ItemStack(shadowCore), "#O#", "OEO", "#O#", '#', Items.ender_pearl, 'O', Blocks.obsidian, 'E', Items.ender_eye);
        addRecipe(new ItemStack(budgetaryShadowCrystal), "#O#", "OGO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'G', Blocks.glowstone);
        addRecipe(new ItemStack(weekShadowCrystal), "#O#", "OEO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'E', shadowCore);
        addRecipe(new ItemStack(mediumShadowCrystal), " Q ", "COC", " Q ", 'Q', Blocks.quartz_block, 'C', weekShadowCrystal, 'O', shadowCore);
        addRecipe(new ItemStack(strongShadowCrystal), " Q ", "COC", " Q ", 'Q', shadowCore, 'C', mediumShadowCrystal, 'O', Items.nether_star);
        addRecipe(new ItemStack(shadowWand), "  Q", " R ", "R  ", 'R', Items.blaze_rod, 'Q', shadowCore);
        addRecipe(new ItemStack(shadowShears), "C C", " S ", " C ", 'C', shadowCore, 'S', Items.shears);
        addRecipe(new ItemStack(webZapper), "OSO", "SCS", "OSO", 'O', Blocks.obsidian, 'S', shadowShears, 'C', shadowCore);
        addRecipe(new ItemStack(floatingBlock), "STS", "TBT", "STS", 'S', Items.string, 'T', Blocks.stone, 'B', Items.water_bucket);
        addRecipe(new ItemStack(stringsCreatorBlock), "OCO", "WSW", "OCO", 'O', Blocks.obsidian, 'C', shadowCore, 'W', webZapper, 'S', artificialSpider);
        addRecipe(new ShapedOreRecipe(new ItemStack(artificialSpider), "EIE", "EPE", "SSS", 'E', Items.spider_eye, 'I', Blocks.iron_block, 'P', Items.poisonous_potato, 'S', Items.string));
        addRecipe(new ItemStack(lightFilter), "OOO", "IWI", "OOO", 'O', Blocks.obsidian, 'I', Items.iron_ingot, 'W', new ItemStack(Items.dye, 1, 15));
        addRecipe(new ItemStack(shadowFilter), "OOO", "IBI", "OOO", 'O', Blocks.obsidian, 'I', Items.iron_ingot, 'B', new ItemStack(Items.dye, 1, 0));
        addRecipe(new ItemStack(lightAbsorbent), "ICI", "CFC", "ICI", 'C', shadowCore, 'F', lightFilter, 'I', Items.iron_ingot);
        addRecipe(new ItemStack(shadowAbsorbent), "ICI", "CFC", "ICI", 'C', shadowCore, 'F', shadowFilter, 'I', Items.iron_ingot);
        addRecipe(new ShapedOreRecipe(new ItemStack(flightStationCore), "SFS", "CNC", "TVT", 'S', absorbedShadow, 'F', lightFilter, 'C', shadowCore, 'N', Items.nether_star, 'T', absorbedLight, 'V', shadowFilter));
        addRecipe(new ShapedOreRecipe(new ItemStack(flightStationBlock), "QGQ", "GCG", "QGQ", 'Q', "blockQuartz", 'G', "blockGlass", 'C', flightStationCore));
        addRecipe(new ShapedOreRecipe(new ItemStack(spidersFood), "FWF", "SSS", "FWF", 'F', Items.rotten_flesh, 'W', "cropWheat", 'S', Items.string));
        addRecipe(new ItemStack(shadowFeeder), "RIR", "ICI", "RIR", 'R', Items.redstone, 'I', Items.iron_ingot, 'C', shadowCore);
        addRecipe(new ItemStack(reinforcedShadowFeeder), "ICI", "CFC", "ICI", 'I', Items.iron_ingot, 'C', shadowCore, 'F', shadowFeeder);
        addRecipe(new ItemStack(advancedShadowFeeder), " S ", "CFC", "OCO", 'S', Items.nether_star, 'C', shadowCore, 'O', Blocks.obsidian, 'F', reinforcedShadowFeeder);
        addRecipe(new ItemStack(shadowPickaxe), "CCC", " P ", " O ", 'C', shadowCore, 'P', Items.iron_pickaxe, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadowShovel), " C ", " S ", " O ", 'C', shadowCore, 'S', Items.iron_shovel, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadowAxe), " CC", " AC", " O ", 'C', shadowCore, 'A', Items.iron_axe, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadowHoe), "C C", " H ", " O ", 'C', shadowCore, 'H', Items.iron_hoe, 'O', Blocks.obsidian);

    }
}

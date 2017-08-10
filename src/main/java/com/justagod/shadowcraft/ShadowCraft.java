package com.justagod.shadowcraft;

import com.justagod.shadowcraft.Blocks.Grower.GrowerBlock;
import com.justagod.shadowcraft.Blocks.Grower.GrowerRecipes;
import com.justagod.shadowcraft.Blocks.Grower.GrowerTile;
import com.justagod.shadowcraft.Blocks.ShadowCore.ShadowCoreBlock;
import com.justagod.shadowcraft.Blocks.ShadowCore.ShadowCoreTile;
import com.justagod.shadowcraft.Blocks.Trasmitters.AdminShadowFlowTransmitter.AdminShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.Blocks.Trasmitters.AdminShadowFlowTransmitter.AdminShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.ChargePads.LavaChargePad;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationBlock;
import com.justagod.shadowcraft.Blocks.FlightStation.FlightStationEntity;
import com.justagod.shadowcraft.Blocks.FloatingBlock.FloatingBlock;
import com.justagod.shadowcraft.Blocks.Trasmitters.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.Blocks.Trasmitters.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.Blocks.StringsCreator.StringsCreatorBlock;
import com.justagod.shadowcraft.Blocks.StringsCreator.StringsCreatorEntity;
import com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterBlock;
import com.justagod.shadowcraft.Blocks.Trasmitters.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterTile;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperBlock;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperEntity;
import com.justagod.shadowcraft.Blocks.WebZapper.WebZapperItemBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerBlock;
import com.justagod.shadowcraft.Blocks.WitherReplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.Fluids.ShadowFluid.ShadowFluidParticles;
import com.justagod.shadowcraft.Items.*;
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
import cpw.mods.fml.common.registry.EntityRegistry;
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
    public static final ShadowCore shadow_core = new ShadowCore();
    public static final WitherReplacerBlock witherReplacer = new WitherReplacerBlock();
    public static final LavaShadowFlowTransmitterBlock lavaShadowFlowTransmitterBlock = new LavaShadowFlowTransmitterBlock();
    public static final FloatingBlock floatingBlock = new FloatingBlock();
    public static final WebZapperBlock webZapper = new WebZapperBlock();
    public static final LavaChargePad lavaChargePad = new LavaChargePad();
    public static final BudgetaryShadowCrystal budgetaryShadowCrystal = new BudgetaryShadowCrystal();
    public static final WeekShadowCrystal weekShadowCrystal = new WeekShadowCrystal();
    public static final MediumShadowCrystal mediumShadowCrystal = new MediumShadowCrystal();
    public static final StrongShadowCrystal strongShadowCrystal = new StrongShadowCrystal();
    public static final ShadowWand shadow_wand = new ShadowWand();
    public static final ShadowShears shadowShears = new ShadowShears();
    public static final ShadowPickaxe shadow_pickaxe = new ShadowPickaxe();
    public static final ShadowAxe shadow_axe = new ShadowAxe();
    public static final ShadowShovel shadow_shovel = new ShadowShovel();
    public static final ShadowHoe shadow_hoe = new ShadowHoe();
    public static final ShadowFeeder shadow_feeder = new ShadowFeeder();
    public static final ReinforcedShadowFeeder reinforced_shadow_feeder = new ReinforcedShadowFeeder();
    public static final ArtificialSpider artificial_spider = new ArtificialSpider();
    public static final StringsCreatorBlock stringsCreatorBlock = new StringsCreatorBlock();
    public static final SpidersFood spiders_food = new SpidersFood();
    public static final LightAbsorbent light_absorbent = new LightAbsorbent();
    public static final ShadowAbsorbent shadow_absorbent = new ShadowAbsorbent();
    public static final AbsorbedLight absorbed_light = new AbsorbedLight();
    public static final AbsorbedShadow absorbed_shadow = new AbsorbedShadow();
    public static final FlightStationBlock flight_station_block = new FlightStationBlock();
    public static final AdminShadowFlowTransmitterBlock admin_shadow_flow_transmitter_block = new AdminShadowFlowTransmitterBlock();
    public static final FlightStationCore flight_station_core = new FlightStationCore();
    public static final LightFilter light_filter = new LightFilter();
    public static final ShadowFilter shadow_filter = new ShadowFilter();
    public static final AdvancedShadowFeeder advanced_shadow_feeder = new AdvancedShadowFeeder();
    public static final ShadowCoreBlock shadow_core_block = (ShadowCoreBlock) new ShadowCoreBlock().setBlockName("shadow_core_block");
    public static final ShadowFluidFlowTransmitterBlock shadow_fluid_transmitter_block = new ShadowFluidFlowTransmitterBlock();
    public static final EarthPlacerUpgradeItem earth_placer_upgrade = new EarthPlacerUpgradeItem();
    public static final PersonLockerUpgradeItem person_locker_upgrade = new PersonLockerUpgradeItem();
    public static final GrowerBlock grower = new GrowerBlock();
    public static final ShadowMetal shadow_metal = new ShadowMetal();
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

        GameRegistry.registerBlock(flight_station_block, "flight_station");
        GameRegistry.registerTileEntity(FlightStationEntity.class, "shadowcraft:flight_station");

        GameRegistry.registerBlock(stringsCreatorBlock, "strings_creator_block");
        GameRegistry.registerTileEntity(StringsCreatorEntity.class, "shadowcraft:string_creator");

        GameRegistry.registerBlock(admin_shadow_flow_transmitter_block, "admin_shadow_flow_transmitter_block");
        GameRegistry.registerTileEntity(AdminShadowFlowTransmitterEntity.class, "shadowcraft:admin_shadow_flow_transmitter");

        GameRegistry.registerBlock(shadow_fluid_transmitter_block, "shadow_fluid_transmitter_block");
        GameRegistry.registerTileEntity(ShadowFluidFlowTransmitterTile.class, "shadowcraft:shadow_fluid_transmitter_block");

        GameRegistry.registerBlock(grower, "grower");
        GameRegistry.registerTileEntity(GrowerTile.class, "shadowcraft:grower");

        //GameRegistry.registerBlock(lavaChargePad, "lava_charge_pad");

        GameRegistry.registerBlock(floatingBlock, "floating_block");
        GameRegistry.registerBlock(shadow_core_block, "shadow_core_block");
        GameRegistry.registerTileEntity(ShadowCoreTile.class, "shadowcraft:shadow_core_block");

        GameRegistry.registerItem(budgetaryShadowCrystal, "budgetary_shadow_crystal");
        GameRegistry.registerItem(weekShadowCrystal, "week_shadow_crystal");
        GameRegistry.registerItem(mediumShadowCrystal, "medium_shadow_crystal");
        GameRegistry.registerItem(strongShadowCrystal, "strong_shadow_crystal");

        GameRegistry.registerItem(shadow_core, "shadow_core");

        GameRegistry.registerItem(shadow_wand, "shadow_wand");
        GameRegistry.registerItem(shadowShears, "shadow_shears");
        GameRegistry.registerItem(shadow_pickaxe, "shadow_pickaxe");
        GameRegistry.registerItem(shadow_axe, "shadow_axe");
        GameRegistry.registerItem(shadow_shovel, "shadow_shovel");
        GameRegistry.registerItem(shadow_hoe, "shadow_hoe");
        GameRegistry.registerItem(shadow_feeder, "shadow_feeder");
        GameRegistry.registerItem(reinforced_shadow_feeder, "reinforced_shadow_feeder");
        GameRegistry.registerItem(artificial_spider, "artificial_spider");
        GameRegistry.registerItem(spiders_food, "spiders_food");
        GameRegistry.registerItem(light_absorbent, "light_absorbent");
        GameRegistry.registerItem(shadow_absorbent, "shadow_absorbent");
        GameRegistry.registerItem(absorbed_light, "absorbed_light");
        GameRegistry.registerItem(absorbed_shadow, "absorbed_shadow");
        GameRegistry.registerItem(light_filter, "light_filter");
        GameRegistry.registerItem(shadow_filter, "shadow_filter");
        GameRegistry.registerItem(flight_station_core, "flight_station_core");
        GameRegistry.registerItem(advanced_shadow_feeder, "advanced_shadow_feeder");
        GameRegistry.registerItem(earth_placer_upgrade, "earth_placer_upgrade");
        GameRegistry.registerItem(person_locker_upgrade, "person_locker_upgrade");
        GameRegistry.registerItem(shadow_metal, "shadow_metal");

        EntityRegistry.registerModEntity(
                ShadowFluidParticles.class,
                "shadowcraft:shadow_fluid_particles",
                EntityRegistry.findGlobalUniqueEntityId(),
                instance,
                20,
                20,
                true
                );


        if (event.getSide() == Side.CLIENT) {

            new ClientProxy().registerRenders();
        }
            MinecraftForge.EVENT_BUS.register(new SCEventHandler());


        registerRecipes();
        ShadowFluids.init();
        GrowerRecipes.init();


    }

    private void registerRecipes() {
        addRecipe(new ItemStack(witherReplacer), " S ", "O#O", "#E#", 'O', ShadowCraft.shadow_core, '#', Blocks.obsidian, 'E', Items.ender_pearl, 'S', absorbed_shadow);
        addRecipe(new ItemStack(shadow_core), "#O#", "OEO", "#O#", '#', Items.ender_pearl, 'O', Blocks.obsidian, 'E', Items.ender_eye);
        addRecipe(new ItemStack(budgetaryShadowCrystal), "#O#", "OGO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'G', Blocks.glowstone);
        addRecipe(new ItemStack(weekShadowCrystal), "#O#", "OEO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'E', shadow_core);
        addRecipe(new ItemStack(mediumShadowCrystal), " Q ", "COC", " Q ", 'Q', Blocks.quartz_block, 'C', weekShadowCrystal, 'O', shadow_core);
        addRecipe(new ItemStack(strongShadowCrystal), " Q ", "COC", " Q ", 'Q', shadow_core, 'C', mediumShadowCrystal, 'O', Items.nether_star);
        addRecipe(new ItemStack(shadow_wand), "  Q", " R ", "R  ", 'R', Items.blaze_rod, 'Q', shadow_core);
        addRecipe(new ItemStack(shadowShears), "C C", " S ", " C ", 'C', shadow_core, 'S', Items.shears);
        addRecipe(new ItemStack(webZapper), "OSO", "SCS", "OSO", 'O', Blocks.obsidian, 'S', shadowShears, 'C', shadow_core);
        addRecipe(new ItemStack(floatingBlock), "STS", "TBT", "STS", 'S', Items.string, 'T', Blocks.stone, 'B', Items.water_bucket);
        addRecipe(new ItemStack(stringsCreatorBlock), "OCO", "WSW", "OCO", 'O', Blocks.obsidian, 'C', shadow_core, 'W', webZapper, 'S', artificial_spider);
        addRecipe(new ShapedOreRecipe(new ItemStack(artificial_spider), "EIE", "EPE", "SSS", 'E', Items.spider_eye, 'I', Blocks.iron_block, 'P', Items.poisonous_potato, 'S', Items.string));
        addRecipe(new ItemStack(light_filter), "OOO", "IWI", "OOO", 'O', Blocks.obsidian, 'I', Items.iron_ingot, 'W', new ItemStack(Items.dye, 1, 15));
        addRecipe(new ItemStack(shadow_filter), "OOO", "IBI", "OOO", 'O', Blocks.obsidian, 'I', Items.iron_ingot, 'B', new ItemStack(Items.dye, 1, 0));
        addRecipe(new ItemStack(light_absorbent), "ICI", "CFC", "ICI", 'C', shadow_core, 'F', light_filter, 'I', Items.iron_ingot);
        addRecipe(new ItemStack(shadow_absorbent), "ICI", "CFC", "ICI", 'C', shadow_core, 'F', shadow_filter, 'I', Items.iron_ingot);
        addRecipe(new ShapedOreRecipe(new ItemStack(flight_station_core), "SFS", "CNC", "TVT", 'S', absorbed_shadow, 'F', light_filter, 'C', shadow_core, 'N', Items.nether_star, 'T', absorbed_light, 'V', shadow_filter));
        addRecipe(new ShapedOreRecipe(new ItemStack(flight_station_block), "QGQ", "GCG", "QGQ", 'Q', shadow_metal, 'G', "blockGlass", 'C', flight_station_core));
        addRecipe(new ShapedOreRecipe(new ItemStack(spiders_food), "FWF", "SSS", "FWF", 'F', Items.rotten_flesh, 'W', "cropWheat", 'S', Items.string));
        addRecipe(new ItemStack(shadow_feeder), "RIR", "ICI", "RIR", 'R', Blocks.redstone_block, 'I', Items.iron_ingot, 'C', shadow_core);
        addRecipe(new ItemStack(reinforced_shadow_feeder), "ICI", "CFC", "ICI", 'I', Items.iron_ingot, 'C', shadow_core, 'F', shadow_feeder);
        addRecipe(new ItemStack(advanced_shadow_feeder), " S ", "CFC", "OCO", 'S', Items.nether_star, 'C', shadow_core, 'O', Blocks.obsidian, 'F', reinforced_shadow_feeder);
        addRecipe(new ItemStack(shadow_pickaxe), "MMM", " O ", " O ", 'M', shadow_metal, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadow_shovel), " M ", " O ", " O ", 'M', shadow_metal, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadow_axe), " MM", " OM", " O ", 'M', shadow_metal, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadow_hoe), " MM", " O ", " O ", 'O', Blocks.obsidian, 'M', shadow_metal);
        addRecipe(new ShadowWand.UpgradeRecipe());
    }
}

package com.justagod.shadowcraft;

import com.justagod.shadowcraft.block.grower.GrowerBlock;
import com.justagod.shadowcraft.block.grower.GrowerRecipes;
import com.justagod.shadowcraft.block.grower.GrowerTile;
import com.justagod.shadowcraft.block.shadowcore.ShadowCoreBlock;
import com.justagod.shadowcraft.block.shadowcore.ShadowCoreTile;
import com.justagod.shadowcraft.block.ShadowMetalBlock;
import com.justagod.shadowcraft.block.spawnersminer.SpawnerItemBlock;
import com.justagod.shadowcraft.block.spawnersminer.SpawnersMinerBlock;
import com.justagod.shadowcraft.block.spawnersminer.SpawnersMinerItem;
import com.justagod.shadowcraft.block.trasmitter.AdminShadowFlowTransmitter.AdminShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.block.trasmitter.AdminShadowFlowTransmitter.AdminShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.block.chargepad.LavaChargePad;
import com.justagod.shadowcraft.block.flightstation.FlightStationBlock;
import com.justagod.shadowcraft.block.flightstation.FlightStationEntity;
import com.justagod.shadowcraft.block.floatingblock.FloatingBlock;
import com.justagod.shadowcraft.block.trasmitter.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterBlock;
import com.justagod.shadowcraft.block.trasmitter.LavaShadowFlowTransmitter.LavaShadowFlowTransmitterEntity;
import com.justagod.shadowcraft.block.stringscreator.StringsCreatorBlock;
import com.justagod.shadowcraft.block.stringscreator.StringsCreatorEntity;
import com.justagod.shadowcraft.block.trasmitter.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterBlock;
import com.justagod.shadowcraft.block.trasmitter.ShadowFluidFlowTransmitter.ShadowFluidFlowTransmitterTile;
import com.justagod.shadowcraft.block.webzapper.WebZapperBlock;
import com.justagod.shadowcraft.block.webzapper.WebZapperEntity;
import com.justagod.shadowcraft.block.webzapper.WebZapperItemBlock;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerBlock;
import com.justagod.shadowcraft.block.witherreplacer.WitherReplacerEntity;
import com.justagod.shadowcraft.fluid.ShadowFluid.ShadowFluidParticles;
import com.justagod.shadowcraft.item.*;
import com.justagod.shadowcraft.item.absorbent.AbsorbedLight;
import com.justagod.shadowcraft.item.absorbent.AbsorbedShadow;
import com.justagod.shadowcraft.item.absorbent.LightAbsorbent;
import com.justagod.shadowcraft.item.absorbent.ShadowAbsorbent;
import com.justagod.shadowcraft.item.feeder.AdvancedShadowFeeder;
import com.justagod.shadowcraft.item.feeder.ReinforcedShadowFeeder;
import com.justagod.shadowcraft.item.feeder.ShadowFeeder;
import com.justagod.shadowcraft.item.crafting.*;
import com.justagod.shadowcraft.item.rechargeable.*;
import com.justagod.shadowcraft.item.shadowcrystal.BudgetaryShadowCrystal;
import com.justagod.shadowcraft.item.shadowcrystal.MediumShadowCrystal;
import com.justagod.shadowcraft.item.shadowcrystal.StrongShadowCrystal;
import com.justagod.shadowcraft.item.shadowcrystal.WeekShadowCrystal;
import com.justagod.shadowcraft.network.ClientProxy;
import com.justagod.shadowcraft.network.CommonProxy;
import com.justagod.shadowcraft.network.GUIHandler;
import com.justagod.shadowcraft.tab.ShadowBlocksTab;
import com.justagod.shadowcraft.tab.ShadowItemsTab;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.BlockColored;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;

import static cpw.mods.fml.common.registry.GameRegistry.addRecipe;
import static cpw.mods.fml.common.registry.GameRegistry.addShapelessRecipe;

@Mod(modid = ShadowCraft.MODID, version = ShadowCraft.VERSION)
public class ShadowCraft {
    public static final String MODID = "shadowcraft";
    public static final String VERSION = "1.0";
    public static final ToolMaterial SHADOW_MATERIAL = EnumHelper.addToolMaterial("SHADOW_MATERIAL", 4, 2897, 3000, 5, 22);
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
    public static final ShadowMetalBlock shadow_metal_block = new ShadowMetalBlock();
    public static final SpawnersMinerBlock spawners_miner_block = new SpawnersMinerBlock();
    public static final SpawnerItemBlock spawner_item_block = new SpawnerItemBlock();
    @SidedProxy(clientSide = "com.justagod.shadowcraft.network.ClientProxy", serverSide = "com.justagod.shadowcraft.network.CommonProxy")
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
        GameRegistry.registerBlock(shadow_metal_block, "shadow_metal_block");
        GameRegistry.registerBlock(spawners_miner_block, SpawnersMinerItem.class, "spawners_miner_block");
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
        GameRegistry.registerItem(spawner_item_block, "shadow_spawner_item_block");

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
        System.out.println(Blocks.wool);


    }

    private void registerRecipes() {
        addRecipe(new ItemStack(witherReplacer), " S ", "O#O", "#E#", 'O', ShadowCraft.shadow_core, '#', Blocks.obsidian, 'E', Items.ender_pearl, 'S', absorbed_shadow);
        addRecipe(new ItemStack(shadow_core), "#O#", "OEO", "#O#", '#', Items.ender_pearl, 'O', Blocks.obsidian, 'E', Items.ender_eye);
        addRecipe(new ItemStack(budgetaryShadowCrystal), "#O#", "OGO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'G', Blocks.glowstone);
        addRecipe(new ItemStack(weekShadowCrystal), "#O#", "OEO", "#O#", '#', Blocks.iron_block, 'O', Blocks.redstone_block, 'E', shadow_core);
        addRecipe(new ItemStack(mediumShadowCrystal), " Q ", "COC", " Q ", 'Q', Blocks.quartz_block, 'C', weekShadowCrystal, 'O', shadow_core);
        addRecipe(new ItemStack(strongShadowCrystal), " Q ", "COC", " Q ", 'Q', shadow_core, 'C', mediumShadowCrystal, 'O', shadow_metal_block);
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
        addRecipe(new ItemStack(advanced_shadow_feeder), " S ", "CFC", "OCO", 'S', Items.nether_star, 'C', shadow_core, 'O', shadow_metal, 'F', reinforced_shadow_feeder);
        addRecipe(new ItemStack(shadow_pickaxe), "MMM", " O ", " O ", 'M', shadow_metal, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadow_shovel), " M ", " O ", " O ", 'M', shadow_metal, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadow_axe), " MM", " OM", " O ", 'M', shadow_metal, 'O', Blocks.obsidian);
        addRecipe(new ItemStack(shadow_hoe), " MM", " O ", " O ", 'O', Blocks.obsidian, 'M', shadow_metal);
        addRecipe(new ItemStack(shadow_metal_block), "MMM", "MMM", "MMM", 'M', shadow_metal);
        addShapelessRecipe(new ItemStack(shadow_wand), shadow_wand);
        addRecipe(new ShadowWand.UpgradeRecipe());
    }
}

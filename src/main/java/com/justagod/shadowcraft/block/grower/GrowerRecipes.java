package com.justagod.shadowcraft.block.grower;

import com.justagod.shadowcraft.block.grower.GrowerTile.GrowRecipe;
import com.justagod.shadowcraft.ShadowCraft;
import net.minecraft.init.Items;

import static com.justagod.shadowcraft.block.grower.GrowerTile.GrowRecipeRegister.registerRecipe;

/**
 * Драсьте, сделано Yuri
 * В 13:24
 */
public final class GrowerRecipes {

    public static final GrowRecipe IRON_TO_ENDER_PEARL = new GrowRecipe(Items.iron_ingot, Items.ender_pearl, 2000);
    public static final GrowRecipe ENDER_EYE_TO_SHADOW_CORE = new GrowRecipe(Items.ender_eye, ShadowCraft.shadow_core, 20000);
    public static final GrowRecipe SHADOW_CORE_TO_SHADOW_METAL = new GrowRecipe(ShadowCraft.shadow_core, ShadowCraft.shadow_metal, 2000);
    public static final GrowRecipe LIGHT_ABSORBENT_TO_ABSORBED_LIGHT = new GrowRecipe(ShadowCraft.light_absorbent, ShadowCraft.absorbed_light, 3000);
    public static final GrowRecipe SHADOW_ABSORBENT_TO_ABSORBED_SHADOW = new GrowRecipe(ShadowCraft.shadow_absorbent, ShadowCraft.absorbed_shadow, 3000);
    // TODO: 10.08.17 Добавить теневой металл

    public static void init() {
        registerRecipe(IRON_TO_ENDER_PEARL, "ender_pearl");
        registerRecipe(ENDER_EYE_TO_SHADOW_CORE, "shadow_core");
        registerRecipe(SHADOW_CORE_TO_SHADOW_METAL, "shadow_metal");
        registerRecipe(LIGHT_ABSORBENT_TO_ABSORBED_LIGHT, "absorbed_light");
        registerRecipe(SHADOW_ABSORBENT_TO_ABSORBED_SHADOW, "absorbed_shadow");
    }
}

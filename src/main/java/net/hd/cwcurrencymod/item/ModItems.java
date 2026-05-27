package net.hd.cwcurrencymod.item;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.component.ModComponents;
import net.hd.cwcurrencymod.component.custom.CocktailContentsComponent;
import net.hd.cwcurrencymod.component.custom.UsesRemainingComponent;
import net.hd.cwcurrencymod.component.custom.tooltips.AdderallPillTooltip;
import net.hd.cwcurrencymod.component.custom.tooltips.PookieBearTooltip;
import net.hd.cwcurrencymod.component.custom.tooltips.StimulantTooltip;
import net.hd.cwcurrencymod.item.custom.AdderallPillItem;
import net.hd.cwcurrencymod.item.custom.CocktailGlassItem;
import net.hd.cwcurrencymod.item.custom.CocktailItem;
import net.hd.cwcurrencymod.item.custom.StimulantItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    public static final Item POOKIE_BEAR = registerItem("pookie_bear", settings ->
            new Item(settings
                    .component(ModComponents.POOKIE_BEAR_TOOLTIP, new PookieBearTooltip())
            ));

    public static final Item GEL_CAPSULE = registerItem("gel_capsule", Item::new);
    public static final Item RAW_STIMULANT = registerItem("raw_stimulant", Item::new);
    public static final Item STIMULANT = registerItem("stimulant", settings ->
            new StimulantItem(settings
                    .food(ModFoodComponents.STIMULANT,ModFoodComponents.STIMULANT_EFFECT)
                    .maxCount(16)
                    .component(ModComponents.STIMULANT_TOOLTIP, new StimulantTooltip()
                    )));
    public static final Item ADDERALL_PILL = registerItem("adderall_pill", settings ->
            new AdderallPillItem(settings
                    .food(ModFoodComponents.ADDERALL_PILL,ModFoodComponents.ADDERALL_PILL_EFFECT)
                    .maxCount(1)
                    .component(ModComponents.ADDERALL_PILL_TOOLTIP, new AdderallPillTooltip())
            ));

    public static final Item CHOTE_COIN = registerItem("chote_coin", setting -> new Item(setting.food(ModFoodComponents.CHOTE_COIN,ModFoodComponents.CHOTE_COIN_EFFECT)));
    public static final Item CHOITE_CENT = registerItem("choite_cent", setting -> new Item(setting.food(ModFoodComponents.CHOITE_CENT,ModFoodComponents.CHOITE_CENT_EFFECT)));

    public static final Item COCKTAIL_GLASS = registerItem("cocktail_glass", CocktailGlassItem::new);

    public static final Item COCKTAIL = registerItem("cocktail", settings ->
            new CocktailItem(settings
                    .maxCount(1)
                    .component(ModComponents.COCKTAIL_CONTENTS, CocktailContentsComponent.DEFAULT)
                    .component(ModComponents.USES_REMAINING, UsesRemainingComponent.COCKTAIL)
                    .component(DataComponentTypes.CONSUMABLE, ConsumableComponents.DRINK)
                    .useRemainder(COCKTAIL_GLASS)
            ));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name)))));
    }

    public static void registerModItems() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Mod Items for " + ChoteWorldCurrencyMod.MOD_ID);
    }
}
package net.hd.cwcurrencymod.component;

import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.component.custom.CocktailContentsComponent;
import net.hd.cwcurrencymod.component.custom.UsesRemainingComponent;
import net.hd.cwcurrencymod.component.custom.tooltips.AdderallPillTooltip;
import net.hd.cwcurrencymod.component.custom.tooltips.PookieBearTooltip;
import net.hd.cwcurrencymod.component.custom.tooltips.StimulantTooltip;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;


public class ModComponents
{
    public static final ComponentType<StimulantTooltip> STIMULANT_TOOLTIP =
            register("stimulant_tooltip", builder -> builder.codec(StimulantTooltip.CODEC));

    public static final ComponentType<AdderallPillTooltip> ADDERALL_PILL_TOOLTIP =
            register("adderall_pill_tooltip", builder -> builder.codec(AdderallPillTooltip.CODEC));

    public static final ComponentType<PookieBearTooltip> POOKIE_BEAR_TOOLTIP =
            register("pookie_bear_tooltip", builder -> builder.codec(PookieBearTooltip.CODEC));

    public static final ComponentType<CocktailContentsComponent> COCKTAIL_CONTENTS =
            register("cocktail_contents", builder -> builder.codec(CocktailContentsComponent.CODEC));

    public static final ComponentType<UsesRemainingComponent> USES_REMAINING =
            register("uses_remaining", builder -> builder.codec(UsesRemainingComponent.CODEC));

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Data Component Types for " + ChoteWorldCurrencyMod.MOD_ID);

        ComponentTooltipAppenderRegistry.addLast(
                ModComponents.ADDERALL_PILL_TOOLTIP
        );

        ComponentTooltipAppenderRegistry.addLast(
                ModComponents.STIMULANT_TOOLTIP
        );

        ComponentTooltipAppenderRegistry.addLast(
                ModComponents.POOKIE_BEAR_TOOLTIP
        );
    }
}

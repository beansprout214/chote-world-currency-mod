package net.hd.cwcurrencymod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.hd.cwcurrencymod.item.ModItems;
import net.hd.cwcurrencymod.item.custom.CocktailItem;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        valueLookupBuilder(ConventionalItemTags.DRINKS)
                .add(ModItems.COCKTAIL);

        valueLookupBuilder(ConventionalItemTags.WATERY_DRINKS)
                .add(ModItems.COCKTAIL);
    }
}

package net.hd.cwcurrencymod.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.hd.cwcurrencymod.client.render.item.property.CocktailUsesProperty;
import net.hd.cwcurrencymod.client.render.tint.custom.CocktailTintSource;
import net.hd.cwcurrencymod.item.ModItems;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.RangeDispatchItemModel;
import net.minecraft.client.render.item.tint.ConstantTintSource;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    private void registerCocktailModels(ItemModelGenerator gen) {
        Identifier base = TextureMap.getId(ModItems.COCKTAIL);
        TintSource tint = new CocktailTintSource();

        // Upload a two-layer model and create a tinted entry for each fill level
        List<RangeDispatchItemModel.Entry> entries = new ArrayList<>();
        ItemModel.Unbaked level1 = null;

        for (int uses = 1; uses <= 5; uses++) {
            Identifier overlay = TextureMap.getSubId(ModItems.COCKTAIL, "_overlay_" + uses);
            Identifier subModelId = ModelIds.getItemSubModelId(ModItems.COCKTAIL, "_" + uses);
            Models.GENERATED_TWO_LAYERS.upload(subModelId, TextureMap.layered(base, overlay), gen.modelCollector);

            ItemModel.Unbaked model = ItemModels.tinted(subModelId, ItemModels.constantTintSource(-1), tint);
            if (uses == 1) level1 = model; // reused as fallback (usesRemaining=0 is below 0.2 threshold)
            entries.add(new RangeDispatchItemModel.Entry(uses * 0.2f, model));
            // thresholds: 0.2, 0.4, 0.6, 0.8, 1.0
        }

        gen.output.accept(ModItems.COCKTAIL,
                ItemModels.rangeDispatch(new CocktailUsesProperty(), 1.0f, entries)
        );
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHOTE_COIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CHOITE_CENT_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.POOKIE_BEAR, Models.GENERATED);

        itemModelGenerator.register(ModItems.CHOTE_COIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHOITE_CENT, Models.GENERATED);

        itemModelGenerator.register(ModItems.GEL_CAPSULE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STIMULANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_STIMULANT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ADDERALL_PILL, Models.GENERATED);

        itemModelGenerator.register(ModItems.COCKTAIL_GLASS, Models.GENERATED);
        registerCocktailModels(itemModelGenerator);

//        itemModelGenerator.registerWithTintedOverlay(ModItems.COCKTAIL, new CocktailTintSource());
    }
}

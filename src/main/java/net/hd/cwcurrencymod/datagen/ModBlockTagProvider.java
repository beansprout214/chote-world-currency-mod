package net.hd.cwcurrencymod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.CHOTE_COIN_BLOCK)
                .add(ModBlocks.CHOITE_CENT_BLOCK);

        valueLookupBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.CHOTE_COIN_BLOCK)
                .add(ModBlocks.CHOITE_CENT_BLOCK);
    }
}
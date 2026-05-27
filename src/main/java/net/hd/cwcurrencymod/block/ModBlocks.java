package net.hd.cwcurrencymod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Function;

public class ModBlocks {
    public static final Block CHOTE_COIN_BLOCK = registerBlock("chote_coin_block",
            properties -> new Block(properties.strength(4f).requiresTool()));

    public static final Block CHOITE_CENT_BLOCK = registerBlock("choite_cent_block",
            properties -> new Block(properties.strength(4f).requiresTool()));


    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Mod Blocks for " + ChoteWorldCurrencyMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(CHOTE_COIN_BLOCK);
            entries.add(CHOITE_CENT_BLOCK);
        });
    }
}
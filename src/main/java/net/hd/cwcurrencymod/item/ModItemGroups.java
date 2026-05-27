package net.hd.cwcurrencymod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.hd.cwcurrencymod.item.custom.CocktailItem;
import net.hd.cwcurrencymod.util.constants.CocktailTypes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ChoteWorldCurrencyMod.MOD_ID, "chote_world_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.CHOTE_COIN))
                    .displayName(Text.translatable("itemgroup.chote-world-currency-mod.chote_currency"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.CHOTE_COIN);
                        entries.add(ModItems.CHOITE_CENT);

                        entries.add(ModBlocks.CHOTE_COIN_BLOCK);
                        entries.add(ModBlocks.CHOITE_CENT_BLOCK);

                        entries.add(ModItems.POOKIE_BEAR);

                        entries.add(ModItems.GEL_CAPSULE);
                        entries.add(ModItems.RAW_STIMULANT);
                        entries.add(ModItems.STIMULANT);
                        entries.add(ModItems.ADDERALL_PILL);

                        entries.add(ModItems.COCKTAIL_GLASS);
                        entries.add(CocktailItem.getCocktail(CocktailTypes.WATER));
                        entries.add(CocktailItem.getCocktail(CocktailTypes.BLAZE_BOURBON));
                        entries.add(CocktailItem.getCocktail(CocktailTypes.BITE_OF_87));
                        entries.add(CocktailItem.getCocktail(CocktailTypes.DEATH_SENTENCE));
                        entries.add(CocktailItem.getCocktail(CocktailTypes.BLAKE_SHAKE));
                        entries.add(CocktailItem.getCocktail(CocktailTypes.CHOTE_FLOAT));
                    })
                    .build());

    public static void registerItemGroups() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Item Groups for " + ChoteWorldCurrencyMod.MOD_ID);
    }
}

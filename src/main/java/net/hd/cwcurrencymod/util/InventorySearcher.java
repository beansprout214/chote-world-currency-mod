package net.hd.cwcurrencymod.util;

import com.mrcrayfish.backpacked.BackpackHelper;
import com.mrcrayfish.backpacked.inventory.BackpackInventory;
import com.mrcrayfish.backpacked.inventory.BackpackedInventoryAccess;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InventorySearcher {

    public int countContainer(ItemStack containerStack, Item item) {
        ContainerComponent container = containerStack.get(DataComponentTypes.CONTAINER);
        if (container == null) return 0;
        return getCountFromItemStackStream(container.stream(), item);
    }

    public int countBundle(ItemStack bundle, Item item) {
        BundleContentsComponent container = bundle.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (container == null) return 0;
        return getCountFromItemStackStream(container.stream(), item);
    }

    public int getCountFromItemStackStream(Stream<ItemStack> stream, Item item) {
        return stream
                .filter(stack -> !stack.isEmpty())
                .filter(stack -> stack.isOf(item))
                .mapToInt(ItemStack::getCount)
                .sum();
    }

    public Set<BackpackInventory> getPlayerBackpackInventories(ServerPlayerEntity player) {
//        ChoteWorldCurrencyMod.LOGGER.info("Fetching {} backpacks",player.getName().getString());
        List<ItemStack> playerBackpacks = BackpackHelper.getBackpacks(player);

        Set<BackpackInventory> inventories = new HashSet<>();
        IntStream.range(0,playerBackpacks.size()).forEach(i -> {
            BackpackInventory inventory = ((BackpackedInventoryAccess)player).backpacked$GetBackpackInventory(i);
            if (inventory != null) {
                inventories.add(inventory);
            }
        });

//        ChoteWorldCurrencyMod.LOGGER.info("Obtained {} backpacks",inventories.size());
        return inventories;
    }

    public int handleItemStack(ItemStack stack, Item targetItem) {
        // container check
        if(stack.getComponents().contains(DataComponentTypes.CONTAINER)) {
//            ChoteWorldCurrencyMod.LOGGER.info("Has CONTAINER data component");
            return countContainer(stack, targetItem);
        }

        // bundle check
        if(stack.getComponents().contains(DataComponentTypes.BUNDLE_CONTENTS)) {
//            ChoteWorldCurrencyMod.LOGGER.info("Has BUNDLE_CONTENTS data component");
            return countBundle(stack, targetItem);
        }

        if(stack.isOf(targetItem)) {
//            ChoteWorldCurrencyMod.LOGGER.info("ItemStack is targetItem");
            return stack.getCount();
        }

//        ChoteWorldCurrencyMod.LOGGER.info("ItemStack is nothingburger");
        return 0;
    }

    public int traverseInventory(Inventory inv, Item targetItem) {
//        ChoteWorldCurrencyMod.LOGGER.info("Traversing inventory of size {} for {}",inv.size(),targetItem.getName().getString());

        int count = 0;

        for(int i = 0; i < inv.size(); i++) {
            ItemStack currStack = inv.getStack(i);
            count += handleItemStack(currStack, targetItem);
        }

//        ChoteWorldCurrencyMod.LOGGER.info("Found {} {}",count, targetItem.getName().getString());
        return count;
    }

    public int traverseAllPlayerInventories(ServerPlayerEntity player, Item targetItem) {
//        ChoteWorldCurrencyMod.LOGGER.info("Traversing all inventorise of {} for {}",player.getName().getString(),targetItem.getName().getString());
        Set<Inventory> inventories = getInventories(player);
        return inventories.stream()
                .mapToInt(inventory -> traverseInventory(inventory, targetItem))
                .sum();
    }

    public Set<Inventory> getInventories(ServerPlayerEntity player) {
//        ChoteWorldCurrencyMod.LOGGER.info("Fetching all inventories of {}",player.getName().getString());
        Set<Inventory> inventories = new HashSet<>();

        inventories.add(player.getInventory());
        inventories.add(player.getEnderChestInventory());
        inventories.addAll(getPlayerBackpackInventories(player));

        return inventories;
    }

    public int logAllItems (ServerPlayerEntity player, MinecraftServer server, Item targetItem) {
        return traverseAllPlayerInventories(player, targetItem);
    }
}

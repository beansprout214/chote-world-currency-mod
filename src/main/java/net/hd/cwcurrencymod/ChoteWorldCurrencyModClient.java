package net.hd.cwcurrencymod;

import net.fabricmc.api.ClientModInitializer;
import net.hd.cwcurrencymod.client.render.ModMapManager;

public class ChoteWorldCurrencyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMapManager.bootstrap();
    }
}

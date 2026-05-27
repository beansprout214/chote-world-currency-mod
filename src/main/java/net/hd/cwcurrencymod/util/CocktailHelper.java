package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.component.custom.CocktailContentsComponent;
import net.hd.cwcurrencymod.data.cocktail_types.CocktailRecord;
import net.hd.cwcurrencymod.util.constants.CocktailTypes;

public class CocktailHelper {
    public static int getColor(CocktailContentsComponent contentsComponent) {
        CocktailTypes type = CocktailTypes.fromId(contentsComponent.record().id());
        if (type == null) {
            ChoteWorldCurrencyMod.LOGGER.error("Attempted to fetch the color of CocktailType with id {}, which doesn't exist",contentsComponent.record().id());
        }
        switch (type) {
            case WATER:
                return 0xFF527cd1;
            case BLAZE_BOURBON:
                return 0xFFff672b;
            case BITE_OF_87:
                return 0xFF965823;
            case DEATH_SENTENCE:
                return 0xFF363636;
            case null:
                break;
        }
        return 0xFFFFFFFF;
    }
}

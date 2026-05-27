package net.hd.cwcurrencymod.util.constants;

public enum CocktailTypes {
    WATER("water"),
    BLAZE_BOURBON("blaze_bourbon"),
    BITE_OF_87("bite_of_87"),
    DEATH_SENTENCE("death_sentence");

    private final String id;

    CocktailTypes(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public static CocktailTypes fromId(String id) {
        for (CocktailTypes type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}

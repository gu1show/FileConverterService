package storage;

import java.util.LinkedHashMap;

public class Artist {
    private final String COUNTRY;
    private final String NAME;
    private final LinkedHashMap<String, Integer> PICTURES;

    public Artist(String country, String name, LinkedHashMap<String, Integer> pictures) {
        this.COUNTRY = country;
        this.NAME = name;
        this.PICTURES = pictures;
    }

    public LinkedHashMap<String, Integer> getPictures() {
        return PICTURES;
    }

    public String getName() {
        return NAME;
    }

    public String getCountry() {
        return COUNTRY;
    }
}

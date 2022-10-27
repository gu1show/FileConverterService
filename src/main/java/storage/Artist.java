package storage;

import java.util.Map;

public class Artist {
    private final String COUNTRY;

    private final String NAME;
    private final Map<String, Integer> PICTURES;

    public Artist(String country, String name, Map<String, Integer> pictures) {
        this.COUNTRY = country;
        this.NAME = name;
        this.PICTURES = pictures;
    }

    public Map<String, Integer> getPictures() {
        return PICTURES;
    }

    public String getName() {
        return NAME;
    }

    public String getCountry() {
        return COUNTRY;
    }
}

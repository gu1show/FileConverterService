package storage;

import java.util.LinkedHashMap;

/**
 * Wrapper of information about artists.
 */
public class Artist {
    /**
     * Artist's country.
     */
    private final String COUNTRY;

    /**
     * Artist's name.
     */
    private final String NAME;

    /**
     * Artist's pictures.
     */
    private final LinkedHashMap<String, Integer> PICTURES;

    /**
     * Creating an artist with country, name and pictures.
     * @param country Place of birth.
     * @param name Artist's name.
     * @param pictures Artist's pictures with their names and years of publication.
     */
    public Artist(String country, String name, LinkedHashMap<String, Integer> pictures) {
        this.COUNTRY = country;
        this.NAME = name;
        this.PICTURES = pictures;
    }

    /**
     * @return Linked hashmap with artist's pictures.
     */
    public LinkedHashMap<String, Integer> getPictures() {
        return PICTURES;
    }

    /**
     * @return Artist's name.
     */
    public String getName() {
        return NAME;
    }

    /**
     * @return Artist's country.
     */
    public String getCountry() {
        return COUNTRY;
    }
}

package storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * Wrapper of information about artists.
 */
@Setter
@Getter
@AllArgsConstructor
public class Artist {
    /**
     * Artist's country.
     */
    @NonNull private final String country;

    /**
     * Artist's name.
     */
    @NonNull private final String name;

    /**
     * Artist's pictures.
     */
    @NonNull private final LinkedHashMap<String, Integer> pictures;
}

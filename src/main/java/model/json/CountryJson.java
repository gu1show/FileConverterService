package model.json;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 * Обёртка для содержимого тега "страна".
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryJson {
    /**
     * Название страны.
     */
    @SerializedName("название")
    private String countryName;

    /**
     * Список художников из данной страны.
     */
    @SerializedName("художник")
    private List<ArtistJson> artistList;
}

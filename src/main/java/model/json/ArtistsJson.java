package model.json;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Обёртка для содержимого тега "художники".
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistsJson {
    /**
     * Список блоков со страной и её художниками.
     */
    @SerializedName("страна")
    private List<CountryJson> countryList;
}

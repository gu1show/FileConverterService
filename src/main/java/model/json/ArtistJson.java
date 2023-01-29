package model.json;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Обёртка для содержимого тега "художник".
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistJson {
    /**
     * ФИО художника.
     */
    @SerializedName("полноеИмя")
    private String name;

    /**
     * Картины художника.
     */
    @SerializedName("картина")
    private List<PictureJson> pictures;
}

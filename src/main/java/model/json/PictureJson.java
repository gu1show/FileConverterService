package model.json;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Обёртка для содержимого тега "картина".
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureJson {
    /**
     * Название картины.
     */
    @SerializedName("название")
    private String name;

    /**
     * Год публикации картины.
     */
    @SerializedName("годПубликации")
    private int publicationYear;
}

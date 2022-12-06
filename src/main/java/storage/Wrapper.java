package storage;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Обёртка с данными о художниках.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wrapper {
    /**
     * Содержимое тега artists.
     */
    @SerializedName("artists")
    private Artists artists;
}

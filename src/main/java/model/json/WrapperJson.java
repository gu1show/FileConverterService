package model.json;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.mapper.Wrapper;

/**
 * Обёртка с данными о художниках.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WrapperJson implements Wrapper {
    /**
     * Содержимое тега "художники".
     */
    @SerializedName("художники")
    private ArtistsJson artists;
}

package storage;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Обёртка для содержимого тега artist.
 */
@XmlRootElement(name = "artist")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    /**
     * ФИО художника.
     */
    @XmlElement(name = "name")
    private String name;

    /**
     * Картины художника.
     */
    @XmlElementWrapper(name = "pictures")
    @XmlElement(name = "picture")
    @SerializedName("picture")
    private List<Picture> pictures;
}

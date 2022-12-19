package model;

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
@XmlRootElement(name = "художник")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    /**
     * ФИО художника.
     */
    @XmlElement(name = "полноеИмя")
    @SerializedName("полноеИмя")
    private String name;

    /**
     * Картины художника.
     */
    @XmlElementWrapper(name = "картины")
    @XmlElement(name = "картина")
    @SerializedName("картина")
    private List<Picture> pictures;
}

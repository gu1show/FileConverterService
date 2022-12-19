package model;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Обёртка для содержимого тега country.
 */
@XmlRootElement(name = "страна")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    /**
     * Название страны.
     */
    @XmlAttribute(name = "название")
    @SerializedName("название")
    private String countryName;

    /**
     * Список художников из данной страны.
     */
    @XmlElement(name = "художник")
    @SerializedName("художник")
    private List<Artist> artistList;
}

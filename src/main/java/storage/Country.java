package storage;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Обёртка для содержимого тега country.
 */
@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    /**
     * Название страны.
     */
    @XmlAttribute(name = "name")
    private String countryName;

    /**
     * Список художников из данной страны.
     */
    @XmlElement(name = "artist")
    private List<Artist> artistList;
}

package model.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Обёртка для содержимого тега "страна".
 */
@XmlRootElement(name = "страна")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryXml {
    /**
     * Название страны.
     */
    @XmlAttribute(name = "название")
    private String countryName;

    /**
     * Список художников из данной страны.
     */
    @XmlElement(name = "художник")
    private List<ArtistXml> artistList;
}

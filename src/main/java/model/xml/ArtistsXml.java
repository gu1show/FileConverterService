package model.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Обёртка для содержимого тега "художники".
 */
@XmlRootElement(name = "художники")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistsXml {
    /**
     * Список блоков со страной и её художниками.
     */
    @XmlElement(name = "страна")
    private List<CountryXml> countryList;
}

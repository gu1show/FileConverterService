package storage;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Обёртка для содержимого тега artists.
 */
@XmlRootElement(name = "artists")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artists {
    /**
     * Список блоков со страной и её художниками.
     */
    @XmlElement(name = "country")
    private List<Country> countryList;
}

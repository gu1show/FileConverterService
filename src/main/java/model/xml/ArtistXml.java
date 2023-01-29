package model.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Обёртка для содержимого тега "художник".
 */
@XmlRootElement(name = "художник")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistXml {
    /**
     * ФИО художника.
     */
    @XmlElement(name = "полноеИмя")
    private String name;

    /**
     * Картины художника.
     */
    @XmlElementWrapper(name = "картины")
    @XmlElement(name = "картина")
    private List<PictureXml> pictures;
}

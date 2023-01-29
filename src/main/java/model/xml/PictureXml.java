package model.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Обёртка для содержимого тега "картина".
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PictureXml {
    /**
     * Название картины.
     */
    @XmlElement(name = "название")
    private String name;

    /**
     * Год публикации картины.
     */
    @XmlElement(name = "годПубликации")
    private int publicationYear;
}

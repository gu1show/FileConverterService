package storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Обёртка для содержимого тега picture.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    /**
     * Название картины.
     */
    @XmlElement(name = "name")
    private String name;

    /**
     * Год публикации картины.
     */
    @XmlElement(name = "publicationYear")
    private int publicationYear;
}

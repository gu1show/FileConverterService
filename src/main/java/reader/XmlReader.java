package reader;


import storage.Artist;
import storage.Artists;
import storage.Country;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.*;

/**
 * Считыватель информации о художниках.
 */
public class XmlReader {
    /**
     * Путь к файлу, откуда считываются данные.
     */
    private final String path;

    /**
     * Создание считывателя с определённого пути к файлу.
     * @param path Путь к XML-файлу.
     */
    public XmlReader(String path) {
        this.path = path;
    }

    /**
     * Считывание информации о стране и её художников с их картинами из XML-файла.
     * @return Map с отношением страна-список картин с годом публикации.
     * @throws JAXBException Если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    final public Map<String, List<Artist>> read() throws JAXBException {
        File file = new File(path);

        JAXBContext jaxbContext = JAXBContext.newInstance(Artists.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        List<Country> listCountryTag = ((Artists) unmarshaller.unmarshal(file)).getCountryList();

        Map<String, List<Artist>> countryAndArtists = Collections.synchronizedMap(new LinkedHashMap<>());
        listCountryTag.forEach(countryTag -> countryAndArtists.put(countryTag.getCountryName(),
                                                                   countryTag.getArtistList()));

        return countryAndArtists;
    }
}

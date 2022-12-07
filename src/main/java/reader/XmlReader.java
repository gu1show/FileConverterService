package reader;

import storage.Artists;
import storage.Wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Считыватель информации о художниках из XML.
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
     * @return Обёртка для хранения информации о художниках.
     * @throws JAXBException Если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    final public Wrapper read() throws JAXBException {
        File file = new File(path);

        JAXBContext jaxbContext = JAXBContext.newInstance(Artists.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Artists listCountryTag = ((Artists) unmarshaller.unmarshal(file));

        return new Wrapper(listCountryTag);
    }
}

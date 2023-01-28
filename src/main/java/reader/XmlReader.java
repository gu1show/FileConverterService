package reader;

import lombok.val;
import model.Artists;
import model.Wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;

/**
 * Считыватель информации о художниках из XML-файла с определённой кодировкой.
 */
public class XmlReader implements BasicReader {
    /**
     * Считывание информации о стране и её художников с их картинами из XML-файла с определённой кодировкой.
     * @param path Путь к XML-файлу, из которого нужно считывать информацию.
     * @param encoding Кодировка, в которой записывается файл.
     * @return Обёртка для хранения информации о художниках.
     * @throws JAXBException Если невозможно создать экземпляр без аргументов у какого-то класса из model.
     * @throws IOException Если нет файла или прав на доступ к нему.
     */
    final public Wrapper read(final String path, final String encoding) throws JAXBException, IOException {
        final Artists listCountryTag;
        try (val input = new BufferedReader(
                                        new InputStreamReader(
                                            new FileInputStream(path), encoding))) {
            val jaxbContext = JAXBContext.newInstance(Artists.class);
            val unmarshaller = jaxbContext.createUnmarshaller();
            listCountryTag = ((Artists) unmarshaller.unmarshal(input));
        }

        return new Wrapper(listCountryTag);
    }
}

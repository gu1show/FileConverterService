package reader;

import model.Artists;
import model.Wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Считыватель информации о художниках из XML-файла с определённой кодировкой.
 */
public class XmlReader implements ConcreteReader {
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
        try (BufferedReader input = new BufferedReader(
                                        new InputStreamReader(
                                            new FileInputStream(path), encoding))) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Artists.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            listCountryTag = ((Artists) unmarshaller.unmarshal(input));
        }

        return new Wrapper(listCountryTag);
    }
}

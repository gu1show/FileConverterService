package reader;

import lombok.val;
import model.xml.ArtistsXml;
import model.xml.WrapperXml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.*;

/**
 * Считыватель информации о художниках из XML-файла с определённой кодировкой.
 */
public class XmlReader implements BasicReader {
    /**
     * Считывание информации о стране и её художников с их картинами из XML-файла с определённой кодировкой.
     * @param inputStreamReader Поток к файлу с художниками.
     * @return Обёртка для хранения информации о художниках.
     * @throws JAXBException Если невозможно создать экземпляр без аргументов у какого-то класса из model.
     * @throws IOException Если нет файла или прав на доступ к нему.
     */
    final public WrapperXml read(final InputStreamReader inputStreamReader) throws JAXBException, IOException {
        final ArtistsXml listCountryTag;
        try (val input = new BufferedReader(inputStreamReader)) {
            val jaxbContext = JAXBContext.newInstance(ArtistsXml.class);
            val unmarshaller = jaxbContext.createUnmarshaller();
            listCountryTag = ((ArtistsXml) unmarshaller.unmarshal(input));
        }

        return new WrapperXml(listCountryTag);
    }
}

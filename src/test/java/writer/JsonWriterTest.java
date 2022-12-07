package writer;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import reader.XmlReader;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/**
 * Тестовый класс для записывателей XmlWriter и JsonWriter.
 */
public class JsonWriterTest {
    /**
     * Путь к исходному XML-файлу.
     */
    private static final String PATH_SOURCE_XML = "src\\test\\resources\\artists.xml";

    /**
     * Путь к исходному JSON-файлу.
     */
    private static final String PATH_SOURCE_JSON = "src\\test\\resources\\artists.json";

    /**
     * Путь к JSON-файлу, который появится при конвертации.
     */
    private static final String PATH_CONVERTED_JSON = "src\\test\\resources\\converted.json";

    /**
     * Проверка, что изначальный и полученный файлы идентичны.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToJson() throws IOException, JAXBException {
        String contentSourceJson = FileUtils.readFileToString(new File(PATH_SOURCE_JSON), "utf-8");

        XmlReader xmlReader = new XmlReader(PATH_SOURCE_XML);
        JsonWriter jsonWriter = new JsonWriter(xmlReader.read());
        jsonWriter.write(PATH_CONVERTED_JSON);

        String contentConvertedJson = FileUtils.readFileToString(new File(PATH_CONVERTED_JSON), "utf-8");

        Assert.assertEquals(contentSourceJson, contentConvertedJson);
    }
}

package writer;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import reader.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.io.Reader;

/**
 * Тестовый класс для записывателей XmlWriter и JsonWriter.
 */
public class XmlAndJsonWriterTest {
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
     * Путь к XML-файлу, который появится при конвертации.
     */
    private static final String PATH_CONVERTED_XML = "src\\test\\resources\\converted.xml";

    /**
     * Проверка, что изначальный и полученный XML-файлы идентичны.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToXml() throws IOException, JAXBException {
        JsonReader jsonReader = new JsonReader();
        XmlWriter xmlWriter = new XmlWriter();
        xmlWriter.write(PATH_CONVERTED_XML, jsonReader.read(PATH_SOURCE_JSON));

        Reader sourceReader = new BufferedReader(new FileReader(PATH_SOURCE_XML));
        Reader convertedFileReader = new BufferedReader(new FileReader(PATH_CONVERTED_XML));

        Assert.assertTrue(IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader));

        sourceReader.close();
        convertedFileReader.close();
    }

    /**
     * Проверка, что изначальный и полученный JSON-файлы идентичны.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToJson() throws IOException, JAXBException {
        XmlReader xmlReader = new XmlReader();
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.write(PATH_CONVERTED_JSON, xmlReader.read(PATH_SOURCE_XML));

        Reader sourceReader = new BufferedReader(new FileReader(PATH_SOURCE_JSON));
        Reader convertedFileReader = new BufferedReader(new FileReader(PATH_CONVERTED_JSON));

        Assert.assertTrue(IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader));

        sourceReader.close();
        convertedFileReader.close();
    }
}

package writer;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import reader.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тестовый класс для записывателей XmlWriter и JsonWriter.
 */
@Execution(ExecutionMode.CONCURRENT)
public class XmlAndJsonWriterTest {
    /**
     * Путь к исходному XML-файлу с кодировкой UTF-8.
     */
    private static final String PATH_SOURCE_XML_UTF_8 = "src\\test\\resources\\artists-utf-8.xml";

    /**
     * Путь к исходному XML-файлу с кодировкой windows-1251.
     */
    private static final String PATH_SOURCE_XML_WINDOWS_1251 = "src\\test\\resources\\artists-windows1251.xml";

    /**
     * Путь к исходному JSON-файлу с кодировкой UTF-8.
     */
    private static final String PATH_SOURCE_JSON_UTF_8 = "src\\test\\resources\\artists-utf-8.json";

    /**
     * Путь к исходному JSON-файлу с кодировкой windows-1251.
     */
    private static final String PATH_SOURCE_JSON_WINDOWS_1251 = "src\\test\\resources\\artists-windows1251.json";

    /**
     * Путь к JSON-файлу, который появится при конвертации, с кодировкой UTF-8.
     */
    private static final String PATH_CONVERTED_JSON_UTF_8 = "src\\test\\resources\\converted-utf-8.json";

    /**
     * Путь к XML-файлу, который появится при конвертации, с кодировкой UTF-8.
     */
    private static final String PATH_CONVERTED_XML_UTF_8 = "src\\test\\resources\\converted-utf-8.xml";

    /**
     * Путь к JSON-файлу, который появится при конвертации, с кодировкой windows-1251.
     */
    private static final String PATH_CONVERTED_JSON_WINDOWS_1251 = "src\\test\\resources\\converted-windows-1251.json";

    /**
     * Путь к XML-файлу, который появится при конвертации, с кодировкой windows-1251.
     */
    private static final String PATH_CONVERTED_XML_WINDOWS_1251 = "src\\test\\resources\\converted-windows-1251.xml";

    /**
     * Переменная, отвечающая за кодировку windows-1251. Нужна для передачи в качестве параметра.
     */
    private static final String encodingWindows1251 = "windows-1251";

    /**
     * Переменная, отвечающая за кодировку windows-1251. Нужна для передачи в качестве параметра.
     */
    private static final String encodingUtf8 = "UTF-8";

    /**
     * Экземпляр класса XmlReader.
     */
    private static final XmlReader xmlReader = new XmlReader();

    /**
     * Экземпляр класса JsonReader.
     */
    private static final JsonReader jsonReader = new JsonReader();

    /**
     * Экземпляр класса XmlWriter.
     */
    private static final XmlWriter xmlWriter = new XmlWriter();

    /**
     * Экземпляр класса JsonWriter.
     */
    private static final JsonWriter jsonWriter = new JsonWriter();

    /**
     * Проверка, что изначальный и полученный XML-файлы идентичны в кодировке windows-1251.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToXmlWindows1251() throws IOException, JAXBException {
        xmlWriter.write(PATH_CONVERTED_XML_WINDOWS_1251,
                        jsonReader.read(PATH_SOURCE_JSON_WINDOWS_1251, encodingWindows1251),
                        encodingWindows1251);

        Reader sourceReader = new BufferedReader(
                                  new InputStreamReader(
                                      new FileInputStream(PATH_SOURCE_XML_WINDOWS_1251)));
        Reader convertedFileReader = new BufferedReader(
                                         new InputStreamReader(
                                             new FileInputStream(PATH_CONVERTED_XML_WINDOWS_1251)));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader));

        sourceReader.close();
        convertedFileReader.close();
    }

    /**
     * Проверка, что изначальный и полученный JSON-файлы идентичны в кодировке windows-1251.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToJsonWindows1251() throws IOException, JAXBException {
        jsonWriter.write(PATH_CONVERTED_JSON_WINDOWS_1251,
                         xmlReader.read(PATH_SOURCE_XML_WINDOWS_1251, encodingWindows1251),
                         encodingWindows1251);

        Reader sourceReader = new BufferedReader(
                                  new InputStreamReader(
                                      new FileInputStream(PATH_SOURCE_JSON_WINDOWS_1251)));
        Reader convertedFileReader = new BufferedReader(
                                         new InputStreamReader(
                                             new FileInputStream(PATH_CONVERTED_JSON_WINDOWS_1251)));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader));

        sourceReader.close();
        convertedFileReader.close();
    }

    /**
     * Проверка, что изначальный и полученный XML-файлы идентичны в кодировке UTF-8.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToXmlUtf8() throws IOException, JAXBException {
        xmlWriter.write(PATH_CONVERTED_XML_UTF_8,
                jsonReader.read(PATH_SOURCE_JSON_UTF_8, encodingUtf8),
                encodingUtf8);

        Reader sourceReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(PATH_SOURCE_XML_UTF_8)));
        Reader convertedFileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(PATH_CONVERTED_XML_UTF_8)));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader));

        sourceReader.close();
        convertedFileReader.close();
    }

    /**
     * Проверка, что изначальный и полученный JSON-файлы идентичны в кодировке UTF-8.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @Test
    public void checkWritingToJsonUtf8() throws IOException, JAXBException {
        jsonWriter.write(PATH_CONVERTED_JSON_UTF_8,
                xmlReader.read(PATH_SOURCE_XML_UTF_8, encodingUtf8),
                encodingUtf8);

        Reader sourceReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(PATH_SOURCE_JSON_UTF_8)));
        Reader convertedFileReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(PATH_CONVERTED_JSON_UTF_8)));

        assertTrue(IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader));

        sourceReader.close();
        convertedFileReader.close();
    }
}

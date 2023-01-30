package writer;

import lombok.val;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reader.*;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тестовый класс для записывателей XmlWriter и JsonWriter.
 */
@Execution(ExecutionMode.CONCURRENT)
public class XmlAndJsonWriterTest {
    /**
     * Путь к исходному XML-файлу с кодировкой UTF-8.
     */
    private static final String PATH_SOURCE_XML_UTF_8 = "artists-utf-8.xml";

    /**
     * Путь к исходному XML-файлу с кодировкой windows-1251.
     */
    private static final String PATH_SOURCE_XML_WINDOWS_1251 = "artists-windows1251.xml";

    /**
     * Путь к исходному JSON-файлу с кодировкой UTF-8.
     */
    private static final String PATH_SOURCE_JSON_UTF_8 = "artists-utf-8.json";

    /**
     * Путь к исходному JSON-файлу с кодировкой windows-1251.
     */
    private static final String PATH_SOURCE_JSON_WINDOWS_1251 = "artists-windows1251.json";

    /**
     * Путь к JSON-файлу, который появится при конвертации, с кодировкой UTF-8.
     */
    private static final String PATH_CONVERTED_JSON_UTF_8 = "converted-utf-8.json";

    /**
     * Путь к XML-файлу, который появится при конвертации, с кодировкой UTF-8.
     */
    private static final String PATH_CONVERTED_XML_UTF_8 = "converted-utf-8.xml";

    /**
     * Путь к JSON-файлу, который появится при конвертации, с кодировкой windows-1251.
     */
    private static final String PATH_CONVERTED_JSON_WINDOWS_1251 = "converted-windows-1251.json";

    /**
     * Путь к XML-файлу, который появится при конвертации, с кодировкой windows-1251.
     */
    private static final String PATH_CONVERTED_XML_WINDOWS_1251 = "converted-windows-1251.xml";

    /**
     * Переменная, отвечающая за кодировку windows-1251. Нужна для передачи в качестве параметра.
     */
    private static final String ENCODING_WINDOWS_1251 = "windows-1251";

    /**
     * Переменная, отвечающая за кодировку windows-1251. Нужна для передачи в качестве параметра.
     */
    private static final String ENCODING_UTF_8 = "UTF-8";

    /**
     * Экземпляр для создания временных файлов.
     */
    @TempDir
    private Path tempDir;

    /**
     * Экземпляр класса XmlReader.
     */
    private final XmlReader xmlReader;

    /**
     * Экземпляр класса JsonReader.
     */
    private final JsonReader jsonReader;

    /**
     * Экземпляр класса XmlWriter.
     */
    private final XmlWriter xmlWriter;

    /**
     * Экземпляр класса JsonWriter.
     */
    private final JsonWriter jsonWriter;

    /**
     * Инициализация объектов для чтения и записи.
     */
    public XmlAndJsonWriterTest() throws JAXBException {
        xmlReader = new XmlReader();
        jsonReader = new JsonReader();
        xmlWriter = new XmlWriter();
        jsonWriter = new JsonWriter();
    }

    /**
     * Проверка, что изначальный и полученный XML-файлы идентичны.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @ParameterizedTest
    @MethodSource("provideParametersWriteToXml")
    public void checkWritingToXml(final String pathConvertedFile, final String pathToConvertFile,
                                  final String pathCorrectFile, final String encoding) throws IOException,
                                                                                              JAXBException {
        Path convertedPath = Files.createFile(tempDir.resolve(pathConvertedFile));

        try (val inputStreamReader = new InputStreamReader(
                                         Objects.requireNonNull(
                                                 getClass()
                                                         .getClassLoader()
                                                         .getResourceAsStream(pathToConvertFile)),
                                         encoding)) {
            xmlWriter.write(convertedPath.toString(),
                            jsonReader.read(inputStreamReader),
                            encoding);
        }

        assertThat(areFilesSame(pathCorrectFile, convertedPath)).isTrue();
    }

    /**
     * Проверка, что изначальный и полученный JSON-файлы идентичны.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    @ParameterizedTest
    @MethodSource("provideParametersWriteToJson")
    public void checkWritingToJson(final String pathConvertedFile, final String pathToConvertFile,
                                   final String pathCorrectFile, final String encoding) throws IOException,
                                                                                               JAXBException {
        Path convertedPath = Files.createFile(tempDir.resolve(pathConvertedFile));

        try (val inputStreamReader = new InputStreamReader(
                                         Objects.requireNonNull(
                                                 getClass()
                                                         .getClassLoader()
                                                         .getResourceAsStream(pathToConvertFile)),
                                         encoding)) {
            jsonWriter.write(convertedPath.toString(),
                             xmlReader.read(inputStreamReader),
                             encoding);
        }

        assertThat(areFilesSame(pathCorrectFile, convertedPath)).isTrue();
    }

    /**
     * Предоставляет параметры для тестов конвертации в XML.
     * @return Параметры для тестов.
     */
    private static Stream<Arguments> provideParametersWriteToXml() {
        return Stream.of(
                Arguments.of(PATH_CONVERTED_XML_WINDOWS_1251, PATH_SOURCE_JSON_WINDOWS_1251,
                        PATH_SOURCE_XML_WINDOWS_1251, ENCODING_WINDOWS_1251),
                Arguments.of(PATH_CONVERTED_XML_UTF_8, PATH_SOURCE_JSON_UTF_8,
                        PATH_SOURCE_XML_UTF_8, ENCODING_UTF_8)
        );
    }

    /**
     * Предоставляет параметры для тестов конвертации в JSON.
     * @return Параметры для тестов.
     */
    private static Stream<Arguments> provideParametersWriteToJson() {
        return Stream.of(
                Arguments.of(PATH_CONVERTED_JSON_WINDOWS_1251, PATH_SOURCE_XML_WINDOWS_1251,
                             PATH_SOURCE_JSON_WINDOWS_1251, ENCODING_WINDOWS_1251),
                Arguments.of(PATH_CONVERTED_JSON_UTF_8, PATH_SOURCE_XML_UTF_8,
                             PATH_SOURCE_JSON_UTF_8, ENCODING_UTF_8)
        );
    }

    /**
     * Проверяет содержимое файлов на идентичность, пропуская разделители.
     * @param pathToCorrectFile Путь к файлу с корректным содержимым.
     * @param convertedPath Путь к полученному в результате конвертации файлу.
     * @return true, если содержимое файлов идентично, false - иначе
     * @throws IOException Если файла не существует.
     */
    private boolean areFilesSame(final String pathToCorrectFile, final Path convertedPath) throws IOException {
        try (val sourceReader = new BufferedReader(
                                    new InputStreamReader(
                                            Objects.requireNonNull(
                                                    getClass()
                                                            .getClassLoader()
                                                            .getResourceAsStream(pathToCorrectFile))));
             val convertedFileReader = new BufferedReader(
                                           new InputStreamReader(
                                               new FileInputStream(convertedPath.toString())))) {
            return IOUtils.contentEqualsIgnoreEOL(sourceReader, convertedFileReader);
        }
    }
}

package benchmark;

import model.json.WrapperJson;
import model.xml.WrapperXml;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import reader.JsonReader;
import reader.XmlReader;
import validator.InputValidator;
import writer.JsonWriter;
import writer.XmlWriter;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Класс для тестирования производительности классов XmlReader, XmlWriter, JsonReader, JsonWriter, InputValidator.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5)
public class ApplicationBenchmark {
    /**
     * Класс, который нужен для хранения аргументов, необходимых для выполнения тестируемых методов.
     */
    @State(Scope.Thread)
    public static class Helper {
        /**
         * Путь к конвертированному JSON-файлу.
         */
        private final String pathToConvertedJson = "converted.json";

        /**
         * Путь к конвертированному XML-файлу.
         */
        private final String pathToConvertedXml = "converted.xml";

        /**
         * Массив из двух аргументов, в котором находится задание, вводимое пользователем.
         */
        private String[] input2Arguments;

        /**
         * Массив из четырё аргументов, в котором находится задание, вводимое пользователем.
         */
        private String[] input4Arguments;

        /**
         * Обёртка с данными о художниках.
         */
        private WrapperJson wrapperJson;

        /**
         * Обёртка с данными о художниках.
         */
        private WrapperXml wrapperXml;

        /**
         * Инициализируются необходимые для тестирования поля.
         * @throws IOException Если файла не существует.
         * @throws JAXBException Срабатывает, если невозможно создать экземпля
         *                       без аргументов у какого-то класса из model.
         */
        @Setup
        public void setup() throws IOException, JAXBException {
            input2Arguments = new String[] {"src/test/resources/artists-utf-8.json", "converted.xml"};

            input4Arguments = new String[] {"src/test/resources/artists-utf-8.json", "converted.xml", "json", "UTF-8"};

            wrapperJson = new JsonReader().read(new InputStreamReader(
                                                    Objects.requireNonNull(
                                                            getClass()
                                                                    .getClassLoader()
                                                                    .getResourceAsStream("artists-utf-8.json")),
                                         "UTF-8"));

            wrapperXml = new XmlReader().read(new InputStreamReader(
                                                  Objects.requireNonNull(
                                                          getClass()
                                                                  .getClassLoader()
                                                                  .getResourceAsStream("artists-utf-8.xml")),
                                       "UTF-8"));
        }
    }

    public static void main(String[] args) throws RunnerException {
        new Runner(new OptionsBuilder()
                       .include(ApplicationBenchmark.class.getSimpleName())
                       .forks(1).build())
                .run();
    }

    /**
     * Тестирует чтение с помощью класса XmlReader.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Срабатывает, если невозможно создать экземпля
     *                       без аргументов у какого-то класса из model.
     */
    @Benchmark
    public void testXmlReader() throws IOException, JAXBException {
        new XmlReader().read(new InputStreamReader(
                                 Objects.requireNonNull(
                                         getClass()
                                                 .getClassLoader()
                                                 .getResourceAsStream("artists-utf-8.xml")),
                      "UTF-8"));
    }

    /**
     * Тестирует чтение с помощью класса JsonReader.
     * @throws IOException Если файла не существует.
     */
    @Benchmark
    public void testJsonReader() throws IOException {
        new JsonReader().read(new InputStreamReader(
                                  Objects.requireNonNull(
                                          getClass()
                                                  .getClassLoader()
                                                  .getResourceAsStream("artists-utf-8.json")),
                       "UTF-8"));
    }

    /**
     * Тестирует запись с помощью класса XmlWriter.
     * @param helper Экземпляр класса Helper, откуда берутся данные для выполнения методов.
     * @throws IOException Если файла не существует.
     * @throws JAXBException Срабатывает, если невозможно создать экземпля
     *                       без аргументов у какого-то класса из model.
     */
    @Benchmark
    public void testXmlWriter(final Helper helper) throws IOException, JAXBException {
        Path tempPath = Path.of(helper.pathToConvertedXml);
        Path convertedPath = Files.createFile(tempPath);

        new XmlWriter().write(convertedPath.toString(), helper.wrapperJson, "UTF-8");

        Files.deleteIfExists(tempPath);
    }

    /**
     * Тестирует запись с помощью класса JsonWriter.
     * @param helper Экземпляр класса Helper, откуда берутся данные для выполнения методов.
     * @throws IOException Если файла не существует.
     */
    @Benchmark
    public void testJsonWriter(final Helper helper) throws IOException {
        Path tempPath = Path.of(helper.pathToConvertedJson);
        Path convertedPath = Files.createFile(tempPath);

        new JsonWriter().write(convertedPath.toString(), helper.wrapperXml, "UTF-8");

        Files.deleteIfExists(tempPath);
    }

    /**
     * Производит валидацию данных при двух аргументах (путь к файлам, откуда считываются данные и куда записываются).
     * @param helper Экземпляр класса Helper, откуда берутся данные для выполнения методов.
     * @throws Exception Если файл не существует, на вход пришло менее 2 аргументов.
     */
    @Benchmark
    public void testValidator2Arguments(final Helper helper) throws Exception {
        Path convertedPath = Files.createFile(Path.of("converted.xml"));

        new InputValidator(helper.input2Arguments).validate();

        Files.deleteIfExists(convertedPath);
    }

    /**
     * Производит валидацию данных при четырёх аргументах
     * (путь к файлам, откуда считываются данные и куда записываются, а также расширение и кодировка исходного файла).
     * @param helper Экземпляр класса Helper, откуда берутся данные для выполнения методов.
     * @throws Exception Если файл не существует, на вход пришло менее 2 аргументов.
     */
    @Benchmark
    public void testValidator4Arguments(final Helper helper) throws Exception {
        Path convertedPath = Files.createFile(Path.of("converted.xml"));

        new InputValidator(helper.input4Arguments).validate();

        Files.deleteIfExists(convertedPath);
    }
}

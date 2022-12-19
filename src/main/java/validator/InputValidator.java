package validator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.any23.encoding.TikaEncodingDetector;

import java.io.*;
import static java.nio.charset.Charset.forName;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Валидатор, проверяющий наличие файла, разрешения на чтение и запись. Определяет кодировку исходного файла.
 */
@Slf4j
@Getter
public class InputValidator {
    /**
     * Аргументы, в которых хранится информация, как производить конвертацию.
     */
    private final String[] arguments;

    /**
     * Кодировка файла, откуда происходит считывание.
     */
    private String encoding;

    /**
     * Создание валидатора с определённой информацией о конвертации.
     * @param arguments Аргументы, в которых хранится информация, как производить конвертацию.
     */
    public InputValidator(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Проверяет наличие файла, права на доступ, определяет кодировку.
     * @throws IOException Происходит, если файла для считывания не существует
     *                     или отсутствуют права на чтение или запись.
     * @throws Exception Происходит, если на вход приходит менее 2 аргументов.
     */
    public void validate() throws Exception {
        if (arguments.length < 2) {
            val message = "Неверный ввод. На входе должно быть как минимум 2 аргумента.";
            log.error(message);
            throw new Exception(message);
        }

        if (!new File(arguments[0]).exists()) {
            val message = "Файл для считывания не существует";
            System.out.println(message);
            throw new IOException(message);
        }

        if (!Files.isReadable(Path.of(arguments[0]))) {
            val message = "Отсутствуют права на чтение";
            System.out.println(message);
            throw new IOException(message);
        }

        if (!new File(arguments[1]).canWrite()) {
            val message = "Отсутствуют права на запись";
            System.out.println(message);
            throw new IOException(message);
        }

        try (InputStream input = new BufferedInputStream(new FileInputStream(arguments[0]))) {
            encoding = forName(new TikaEncodingDetector().guessEncoding(input)).toString();
        }
    }
}

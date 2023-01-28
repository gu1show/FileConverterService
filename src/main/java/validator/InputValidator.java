package validator;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.any23.encoding.TikaEncodingDetector;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
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
            log.error(message);
            throw new IOException(message);
        }

        if (!Files.isReadable(Path.of(arguments[0]))) {
            val message = "Отсутствуют права на чтение";
            log.error(message);
            throw new IOException(message);
        }

        if (!new File(arguments[1]).canWrite()) {
            val message = "Отсутствуют права на запись";
            log.error(message);
            throw new IOException(message);
        }

        if (!canConvert()) {
            val message = "Неверный ввод. Один из файлов должен иметь расширение XML, другой - JSON.";
            log.error(message);
            throw new IllegalArgumentException(message);
        }

        if (encoding == null) {
            try (val input = new BufferedInputStream(
                                 new FileInputStream(arguments[0]))) {
                encoding = new TikaEncodingDetector().guessEncoding(input);
            }
        } else {
            encoding = encoding.toLowerCase();
        }
    }

    /**
     * Проверяет возможность конвертации файлов.
     * @return true, если конвертатор может провести конвертацию, false - иначе.
     */
    private boolean canConvert() {
        val firstFileExtension = FilenameUtils.getExtension(arguments[0]);
        val secondFileExtension = FilenameUtils.getExtension(arguments[1]);

        if ((StringUtils.equals(firstFileExtension, "xml")) && (StringUtils.equals(secondFileExtension, "json"))) {
            return true;
        }

        return (StringUtils.equals(firstFileExtension, "json")) && (StringUtils.equals(secondFileExtension, "xml"));
    }
}

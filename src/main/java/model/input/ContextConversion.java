package model.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * Хранилище обработанных данных.
 */
@Getter
@AllArgsConstructor
public class ContextConversion {
    /**
     * Путь к файлу, из которого считываются данные.
     */
    @NonNull private final String inputPath;

    /**
     * Путь к файлу, в который записываются данные.
     */
    @NonNull private final String outputPath;

    /**
     * Расширение файла, из которого считываются данные.
     */
    private String extension;

    /**
     * Кодировка файлов.
     */
    private String encoding;

    /**
     * Создание объекта, в котором хранятся пути к исходному и полученному в результате конвертации файлам.
     * @param inputPath Путь к файлу, из которого считываются данные.
     * @param outputPath Путь к файлу, в который производится конвертация.
     */
    public ContextConversion(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    /**
     * Создание объекта, в котором хранятся пути к исходному и полученному в результате конвертации файлам,
     * расширение исходного файла.
     * @param inputPath Путь к файлу, из которого считываются данные.
     * @param outputPath Путь к файлу, в который производится конвертация.
     * @param extension Расширение исходного файла.
     */
    public ContextConversion(String inputPath, String outputPath, String extension) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.extension = extension;
    }
}

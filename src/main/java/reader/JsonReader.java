package reader;

import com.google.gson.Gson;
import storage.Wrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Считыватель информации о художниках из JSON.
 */
public class JsonReader {
    /**
     * Путь к файлу, откуда считываются данные.
     */
    private final String path;

    /**
     * Создание считывателя с определённого пути к файлу.
     * @param path Путь к JSON-файлу.
     */
    public JsonReader(String path) {
        this.path = path;
    }

    /**
     * Считывание информации о стране и её художников с их картинами из JSON-файла.
     * @return Обёртка для хранения информации о художниках.
     * @throws IOException Если файла не существует.
     */
    public Wrapper read() throws IOException {
        String content = Files.readString(Path.of(path));

        return new Gson().fromJson(content, Wrapper.class);
    }
}

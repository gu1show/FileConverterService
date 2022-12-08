package reader;

import com.google.gson.Gson;
import storage.Wrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Считыватель информации о художниках из JSON.
 */
public class JsonReader implements Reader {
    /**
     * Считывание информации о стране и её художников с их картинами из JSON-файла.
     * @param path Путь к JSON-файлу, из которого нужно считывать информацию.
     * @return Обёртка для хранения информации о художниках.
     * @throws IOException Если файла не существует.
     */
    public Wrapper read(final String path) throws IOException {
        String content = Files.readString(Path.of(path));

        return new Gson().fromJson(content, Wrapper.class);
    }
}

package reader;

import com.google.gson.Gson;
import lombok.val;
import model.json.WrapperJson;

import java.io.*;

/**
 * Считыватель информации о художниках из JSON-файла с определённой кодировкой.
 */
public class JsonReader implements BasicReader {
    /**
     * Считывание информации о стране и её художников с их картинами из JSON-файла с определённой кодировкой.
     * @param path Путь к JSON-файлу, из которого нужно считывать информацию.
     * @param encoding Кодировка, в которой записывается файл.
     * @return Обёртка для хранения информации о художниках.
     * @throws IOException Если файла не существует или нет прав доступа на чтение.
     */
    public WrapperJson read(final String path, final String encoding) throws IOException {
        try (val input = new BufferedReader(
                                        new InputStreamReader(
                                            new FileInputStream(path), encoding))) {
            return new Gson().fromJson(input, WrapperJson.class);
        }
    }
}

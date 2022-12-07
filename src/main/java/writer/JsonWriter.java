package writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import storage.Wrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Записыватель информации о художниках в JSON-файл.
 */
public class JsonWriter {
    /**
     * Обёртка для хранения информации о художниках.
     */
    private final Wrapper artistsWrapper;

    /**
     * Создание записывателя информации о художниках.
     * @param artistsWrapper Обёртка с данными о художниках.
     */
    public JsonWriter(Wrapper artistsWrapper) {
        this.artistsWrapper = artistsWrapper;
    }

    /**
     * Записывает информацию о художниках в JSON-файл.
     * @param path Путь, куда записывается информация о художниках.
     * @throws IOException Если файла не существует.
     */
    public void write(final String path) throws IOException {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(artistsWrapper, writer);
        }
    }
}

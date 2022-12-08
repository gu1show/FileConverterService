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
public class JsonWriter implements writer.Writer {
    /**
     * Записывает информацию о художниках в JSON-файл.
     * @param path Путь, куда записывается информация о художниках.
     * @param artistsWrapper Обёртка с данными о художниках.
     * @throws IOException Если файла не существует.
     */
    public void write(final String path, Wrapper artistsWrapper) throws IOException {
        try (Writer writer = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(artistsWrapper, writer);
        }
    }
}

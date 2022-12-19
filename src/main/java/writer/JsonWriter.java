package writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Wrapper;

import java.io.*;

/**
 * Записыватель информации о художниках в JSON-файл с определённой кодировкой.
 */
public class JsonWriter implements ConcreteWriter {
    /**
     * Записывает информацию о художниках в JSON-файл по заданной кодировке.
     * @param path Путь, куда записывается информация о художниках.
     * @param artistsWrapper Обёртка с данными о художниках.
     * @param encoding Кодировка, в которой записывается файл.
     * @throws IOException Возникает, если нельзя записать в файл.
     */
    public void write(final String path, final Wrapper artistsWrapper, final String encoding) throws IOException {
        try (OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(path), encoding)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(artistsWrapper, output);
        }
    }
}

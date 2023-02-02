package writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.val;
import model.mapper.Wrapper;
import model.mapper.WrapperMapper;
import model.xml.WrapperXml;
import org.mapstruct.factory.Mappers;

import java.io.*;

/**
 * Записыватель информации о художниках в JSON-файл с определённой кодировкой.
 */
public class JsonWriter implements BasicWriter {
    /**
     * Экземпляр класса Gson, который проводит конвертацию в JSON-файл.
     */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    /**
     * Записывает информацию о художниках в JSON-файл по заданной кодировке.
     * @param path Путь, куда записывается информация о художниках.
     * @param artistsWrapper Обёртка с данными о художниках.
     * @param encoding Кодировка, в которой записывается файл.
     * @throws IOException Возникает, если нельзя записать в файл.
     */
    public void write(final String path, final Wrapper artistsWrapper, final String encoding) throws IOException {
        try (val output = new OutputStreamWriter(
                              new FileOutputStream(path), encoding)) {
            GSON.toJson(getConvertedWrapper(artistsWrapper), output);
        }
    }

    /**
     * Конвертирует WrapperXml в WrapperJson.
     * @param artistsWrapper Обёртка с данными о художниках в виде WrapperXml.
     * @return Обёртка с данными о художниках в виде WrapperJson.
     */
    private Wrapper getConvertedWrapper(final Wrapper artistsWrapper) {
        return Mappers.getMapper(WrapperMapper.class).wrapperXmlToJson((WrapperXml) artistsWrapper);
    }
}

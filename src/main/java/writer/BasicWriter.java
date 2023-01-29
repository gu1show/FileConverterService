package writer;

import model.json.WrapperJson;
import model.mapper.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Интерфейс для записывания данных в файл.
 */
public interface BasicWriter {
    void write(String path, Wrapper artistsWrapper, String encoding) throws IOException, JAXBException;
}

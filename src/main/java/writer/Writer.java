package writer;

import storage.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Интерфейс для записывания данных в файл.
 */
public interface Writer {
    void write(String path, Wrapper artistsWrapper) throws IOException, JAXBException;
}

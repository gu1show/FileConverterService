package writer;

import model.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Интерфейс для записывания данных в файл.
 */
public interface ConcreteWriter {
    void write(String path, Wrapper artistsWrapper, String encoding) throws IOException, JAXBException;
}

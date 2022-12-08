package reader;

import storage.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Интерфейс для считывания данных из файла.
 */
public interface Reader {
    Wrapper read(String path) throws JAXBException, IOException;
}

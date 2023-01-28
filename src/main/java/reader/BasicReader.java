package reader;

import model.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Интерфейс для считывания данных из файла.
 */
public interface BasicReader {
    Wrapper read(String path, String encoding) throws JAXBException, IOException;
}

package reader;

import model.mapper.Wrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Интерфейс для считывания данных из файла.
 */
public interface BasicReader {
    Wrapper read(InputStreamReader inputStreamReader) throws JAXBException, IOException;
}

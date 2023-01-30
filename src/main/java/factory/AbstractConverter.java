package factory;

import reader.BasicReader;
import writer.BasicWriter;

import javax.xml.bind.JAXBException;

/**
 * Интерфейс для создания конвертера.
 */
public interface AbstractConverter {
    BasicReader getReader() throws JAXBException;

    BasicWriter getWriter() throws JAXBException;
}

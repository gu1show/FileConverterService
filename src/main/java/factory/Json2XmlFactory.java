package factory;

import reader.BasicReader;
import reader.JsonReader;
import writer.BasicWriter;
import writer.XmlWriter;

import javax.xml.bind.JAXBException;

/**
 * Фабрика для конвертации из JSON в XML.
 */
public class Json2XmlFactory implements AbstractConverter {
    /**
     * Создаёт экземпляр класса JsonReader.
     * @return JsonReader.
     */
    @Override
    public BasicReader getReader() {
        return new JsonReader();
    }

    /**
     * Создаёт экземпляр класса XmlWriter.
     * @return XmlReader.
     */
    @Override
    public BasicWriter getWriter() throws JAXBException {
        return new XmlWriter();
    }
}

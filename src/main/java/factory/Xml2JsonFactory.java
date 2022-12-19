package factory;

import reader.ConcreteReader;
import reader.XmlReader;
import writer.JsonWriter;
import writer.ConcreteWriter;

/**
 * Фабрика для конвертации из XML в JSON.
 */
public class Xml2JsonFactory implements AbstractConverter {
    /**
     * Создаёт экземпляр класса XmlReader.
     * @return XmlReader.
     */
    @Override
    public ConcreteReader getReader() {
        return new XmlReader();
    }

    /**
     * Создаёт экземпляр класса JsonWriter.
     * @return JsonWriter.
     */
    @Override
    public ConcreteWriter getWriter() {
        return new JsonWriter();
    }
}

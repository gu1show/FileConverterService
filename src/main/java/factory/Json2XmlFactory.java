package factory;

import reader.JsonReader;
import reader.ConcreteReader;
import writer.ConcreteWriter;
import writer.XmlWriter;

/**
 * Фабрика для конвертации из JSON в XML.
 */
public class Json2XmlFactory implements AbstractConverter {
    /**
     * Создаёт экземпляр класса JsonReader.
     * @return JsonReader.
     */
    @Override
    public ConcreteReader getReader() {
        return new JsonReader();
    }

    /**
     * Создаёт экземпляр класса XmlWriter.
     * @return XmlReader.
     */
    @Override
    public ConcreteWriter getWriter() {
        return new XmlWriter();
    }
}

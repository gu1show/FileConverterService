package factory;

import reader.BasicReader;
import reader.JsonReader;
import writer.BasicWriter;
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
    public BasicReader getReader() {
        return new JsonReader();
    }

    /**
     * Создаёт экземпляр класса XmlWriter.
     * @return XmlReader.
     */
    @Override
    public BasicWriter getWriter() {
        return new XmlWriter();
    }
}

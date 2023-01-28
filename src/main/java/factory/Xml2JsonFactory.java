package factory;

import reader.BasicReader;
import reader.XmlReader;
import writer.BasicWriter;
import writer.JsonWriter;

/**
 * Фабрика для конвертации из XML в JSON.
 */
public class Xml2JsonFactory implements AbstractConverter {
    /**
     * Создаёт экземпляр класса XmlReader.
     * @return XmlReader.
     */
    @Override
    public BasicReader getReader() {
        return new XmlReader();
    }

    /**
     * Создаёт экземпляр класса JsonWriter.
     * @return JsonWriter.
     */
    @Override
    public BasicWriter getWriter() {
        return new JsonWriter();
    }
}

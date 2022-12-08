package factory;

import reader.Reader;
import reader.XmlReader;
import writer.JsonWriter;
import writer.Writer;

/**
 * Фабрика для конвертации из XML в JSON.
 */
public class Xml2JsonFactory implements AbstractConverter {
    /**
     * Создаёт экземпляр класса XmlReader.
     * @return XmlReader.
     */
    @Override
    public Reader read() {
        return new XmlReader();
    }

    /**
     * Создаёт экземпляр класса JsonWriter.
     * @return JsonWriter.
     */
    @Override
    public Writer write() {
        return new JsonWriter();
    }
}

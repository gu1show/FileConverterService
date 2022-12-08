package factory;

import reader.JsonReader;
import reader.Reader;
import writer.Writer;
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
    public Reader read() {
        return new JsonReader();
    }

    /**
     * Создаёт экземпляр класса XmlWriter.
     * @return XmlReader.
     */
    @Override
    public Writer write() {
        return new XmlWriter();
    }
}

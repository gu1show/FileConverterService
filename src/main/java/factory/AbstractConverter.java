package factory;

import reader.Reader;
import writer.Writer;

/**
 * Интерфейс для создания конвертера.
 */
public interface AbstractConverter {
    Reader read();

    Writer write();
}

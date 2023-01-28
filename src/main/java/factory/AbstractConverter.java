package factory;

import reader.BasicReader;
import writer.BasicWriter;

/**
 * Интерфейс для создания конвертера.
 */
public interface AbstractConverter {
    BasicReader getReader();

    BasicWriter getWriter();
}

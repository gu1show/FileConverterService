package factory;

import reader.ConcreteReader;
import writer.ConcreteWriter;

/**
 * Интерфейс для создания конвертера.
 */
public interface AbstractConverter {
    ConcreteReader getReader();

    ConcreteWriter getWriter();
}

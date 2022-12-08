import factory.Converter;
import lombok.NonNull;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Запускающий класс.
 */
public class Main {
    /**
     * Запускает конвертер из JSON в XML или наоборот.
     * @param args Массив аргументов входной строки.
     *             Первый элемент содержит путь к исходному файлу.
     *             Второй элемент содержит путь, куда записывается конвертированный файл.
     * @throws IOException Если файл не существует.
     * @throws JAXBException Срабатывает, если невозможно создать экземпляр
     * без аргументов у какого-то класса из storage.
     */
    public static void main(@NonNull String[] args) throws IOException, JAXBException {
        Converter converter = new Converter(args);
        converter.convert();
    }
}

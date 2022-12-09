import factory.Converter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Запускающий класс.
 */
@Slf4j
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
        log.info("Программа запущена.");
        Converter converter = new Converter(args);
        try {
            converter.convert();
            log.info("Программа завершена.");
            System.out.println("Конвертация прошла успешно.");
        } catch (JAXBException exception) {
            System.out.println("Произошла внутренняя ошибка.");
            throw new JAXBException(exception);
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при считывании из файла или записи в файл.");
            throw new IOException(exception);
        }
    }
}

import lombok.NonNull;
import org.apache.commons.io.FilenameUtils;
import reader.*;
import writer.*;

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
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    public static void main(@NonNull String[] args) throws IOException, JAXBException {
        final String errorMessage = """
                
                Неверный ввод. Ввод должен сорержать ровна 2 пути к файлам:
                1) Существующий файл, который вы хотите конвертировать с разрешением .xml или .json.
                2) Новый сконвертированный файл разрешения .xml или .json, но отличающийся от первого.
                """;

        if (args.length != 2) {
            throw new IllegalArgumentException(errorMessage);
        }

        final String firstExtension = args[0];
        final String secondExtension = args[1];

        if ((FilenameUtils.getExtension(firstExtension).equals("xml")) &&
            (FilenameUtils.getExtension(secondExtension).equals("json"))) {
            XmlReader reader = new XmlReader(firstExtension);
            JsonWriter writer = new JsonWriter(reader.read());
            writer.write(secondExtension);

            System.out.println("Converted successfully!");

            return;
        }

        if ((FilenameUtils.getExtension(firstExtension).equals("json")) &&
            (FilenameUtils.getExtension(secondExtension).equals("xml"))) {
            JsonReader jsonReader = new JsonReader(firstExtension);
            XmlWriter writer = new XmlWriter(jsonReader.read());
            writer.write(secondExtension);

            System.out.println("Converted successfully!");

            return;
        }

        throw new IllegalArgumentException("Неверный ввод. На входе должны быть JSON-файл и XML-файл.");
    }
}

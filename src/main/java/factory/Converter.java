package factory;

import lombok.extern.slf4j.Slf4j;
import reader.Reader;
import storage.Wrapper;
import writer.Writer;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Конвертер файла из одного типа в другой.
 */
@Slf4j
public class Converter {
    /**
     * Аргументы, в которых хранится информация, как производить конвертацию.
     */
    private final String[] arguments;

    /**
     * Создание конвертера с определённой информацией о конвертации.
     * @param arguments Аргументы, в которых хранится информация, как производить конвертацию.
     */
    public Converter(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Конвертирует файлы из одного типа в другой.
     * @throws JAXBException Если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     * @throws IOException Если файла не существует.
     */
    public void convert() throws JAXBException, IOException {
        if ((arguments.length != 2) && (arguments.length != 3)) {
            log.error("Неверный ввод. На входе должно быть 2 или 3 аргумента.");
            throw new IllegalArgumentException("Неверный ввод. На входе должно быть 2 или 3 аргумента");
        }

        AbstractConverter converter = createAbstractConverter();

        log.info("Начинается считывание.");
        final Reader reader = converter.read();
        Wrapper wrapper;
        try {
            wrapper = reader.read(arguments[0]);
        } catch (JAXBException exception) {
            String message = "Невозможно создать экземпляр без аргументов у какого-то класса из пакета storage.";
            log.error(message);
            throw new JAXBException(message);
        } catch (IOException exception) {
            String message = "Не существует файла, из которого нужно считывать данные.";
            log.error(message);
            throw new IOException(message);
        }

        log.info("Считывание завершено. Начинается запись.");

        final Writer writer = converter.write();
        try {
            writer.write(arguments[1], wrapper);
        } catch (IOException exception) {
            String message = "Невозможно создать файл для записи.";
            log.error(message);
            throw new IOException(exception);
        } catch (JAXBException exception) {
            String message = "Невозможно создать экземпляр без аргументов у какого-то класса из пакета storage.";
            log.error(message);
            throw new JAXBException(exception);
        }
    }

    /**
     * Создаёт конвертер в зависимости от пришедших данных.
     * @return Конвертер определённого вида.
     */
    private AbstractConverter createAbstractConverter() {
        ConverterMaker converterMaker = new ConverterMaker();
        ConverterType type;
        if (arguments.length == 2) {
            type = converterMaker.determineTypeOfConversion(arguments[0], arguments[1]);
        } else {
            type = converterMaker.determineTypeOfConversion(arguments[2]);
        }

        return converterMaker.makeConverter(type);
    }
}

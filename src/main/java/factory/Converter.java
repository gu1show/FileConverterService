package factory;

import org.apache.commons.io.FilenameUtils;
import reader.Reader;
import storage.Wrapper;
import writer.Writer;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Конвертер файла из одного типа в другой.
 */
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
            throw new IllegalArgumentException("Неверный ввод. На входе должно быть 2 аргумента");
        }

        final String firstExtension = FilenameUtils.getExtension(arguments[0]);
        final String secondExtension = FilenameUtils.getExtension(arguments[1]);

        ConverterMaker converterMaker = new ConverterMaker();
        ConverterType type;
        if (arguments.length == 2) {
            type = converterMaker.determineTypeOfConversion(firstExtension, secondExtension);
        } else {
            type = converterMaker.determineTypeOfConversion(arguments[2]);
        }
        AbstractConverter converter = converterMaker.makeConverter(type);

        Reader reader = converter.read();
        Wrapper wrapper = reader.read(arguments[0]);
        Writer writer = converter.write();
        writer.write(arguments[1], wrapper);
    }
}

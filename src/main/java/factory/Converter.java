package factory;

import lombok.extern.slf4j.Slf4j;
import reader.ConcreteReader;
import model.Wrapper;
import validator.InputValidator;
import writer.ConcreteWriter;

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
     * Кодировка, в которой считаются и записываются файлы.
     */
    private final String encoding;

    /**
     * Определённый считыватель.
     */
    private final ConcreteReader concreteReader;

    /**
     * Определённый записыватель.
     */
    private final ConcreteWriter concreteWriter;

    /**
     * Создание конвертера с определённой информацией о конвертации.
     * @param inputValidator Валидатор, который проверил на доступность для записи и чтения представленные файлы.
     *                       Определяет кодировку введённого файла.
     */
    public Converter(InputValidator inputValidator) throws Exception {
        inputValidator.validate();
        this.arguments = inputValidator.getArguments();
        this.encoding = inputValidator.getEncoding();

        AbstractConverter converter = createAbstractConverter();
        concreteReader = converter.getReader();
        concreteWriter = converter.getWriter();
    }

    /**
     * Конвертирует файлы из одного типа в другой.
     * @throws JAXBException Если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     * @throws IOException Если файла не существует, отсутствуют права на чтение или запись.
     */
    public void convert() throws JAXBException, IOException {
        log.info("Начинается считывание.");
        Wrapper wrapper = concreteReader.read(arguments[0], encoding);

        log.info("Считывание завершено. Начинается запись.");
        concreteWriter.write(arguments[1], wrapper, encoding);
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

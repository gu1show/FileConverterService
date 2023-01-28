package factory;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import model.input.ContextConversion;
import reader.BasicReader;
import writer.BasicWriter;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Конвертер файла из одного типа в другой.
 */
@Slf4j
public class Converter {
    /**
     * Класс, содержащий в себе путь к исходному файлу, к полученному файлу, расширению исходного файла, кодировке.
     */
    private final ContextConversion contextConversion;

    /**
     * Определённый считыватель.
     */
    private final BasicReader concreteReader;

    /**
     * Определённый записыватель.
     */
    private final BasicWriter concreteWriter;

    /**
     * Создание конвертера с определённой информацией о конвертации.
     * @param contextConversion Информация о конвертации, полученная путём обработки входных параметров.
     */
    public Converter(ContextConversion contextConversion) {
        this.contextConversion = contextConversion;

        val converter = createAbstractConverter();
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

        log.info("Считывание завершено. Начинается запись.");
        concreteWriter.write(contextConversion.getOutputPath(),
                             concreteReader.read(contextConversion.getInputPath(), contextConversion.getEncoding()),
                             contextConversion.getEncoding());
    }

    /**
     * Создаёт конвертер в зависимости от пришедших данных.
     * @return Конвертер определённого вида.
     */
    private AbstractConverter createAbstractConverter() {
        val converterMaker = new ConverterMaker();
        ConverterType type;
        if (contextConversion.getExtension() == null) {
            type = converterMaker.determineTypeOfConversion(contextConversion.getInputPath(),
                                                            contextConversion.getOutputPath());
        } else {
            type = converterMaker.determineTypeOfConversion(contextConversion.getExtension());
        }

        return converterMaker.makeConverter(type);
    }
}

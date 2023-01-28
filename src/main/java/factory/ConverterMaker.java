package factory;

import org.apache.commons.lang3.StringUtils;

/**
 * Класс для создания конвертера.
 */
public class ConverterMaker {
    /**
     * Создаёт конвертер определённого типа.
     * @param type Тип конвертера, который нужно создать.
     * @return Класс для конвертации файла из одного типа в другой.
     */
    public AbstractConverter makeConverter(ConverterType type) {
        return switch (type) {
            case XML_2_JSON -> new Xml2JsonFactory();
            case JSON_2_XML -> new Json2XmlFactory();
            default -> throw new IllegalArgumentException("Неверный ввод. Один из файлов " +
                                                          "должен иметь расширение XML, другой - JSON.");
        };
    }

    /**
     * Определяет тип конвертера.
     * @param source Расширение файла, из которого считываются данные.
     * @param destination Расширение файла, в который записываются данные.
     * @return Тип конвертера.
     */
    public ConverterType determineTypeOfConversion(final String source, final String destination) {
        if ((StringUtils.equals(source, "xml")) && (StringUtils.equals(destination, "json"))) {
            return ConverterType.XML_2_JSON;
        }

        return ConverterType.JSON_2_XML;
    }

    /**
     * Определяет тип конвертера.
     * @param source Расширение файла, из которого считываются данные.
     * @return Тип конвертера.
     */
    public ConverterType determineTypeOfConversion(final String source) {
        if (StringUtils.equals(source, "xml")) {
            return ConverterType.XML_2_JSON;
        }

        return ConverterType.JSON_2_XML;
    }
}

package factory;

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
        switch (type) {
            case XML_2_JSON:
                return new Xml2JsonFactory();
            case JSON_2_XML:
                return new Json2XmlFactory();
            default:
                throw new IllegalArgumentException("Неверный ввод. Один из файлов " +
                                                   "должен иметь расширение XML, другой - JSON.");
        }
    }

    /**
     * Определяет тип конвертера.
     * @param source Расширение файла, из которого считываются данные.
     * @param destination Расширение файла, в который записываются данные.
     * @return Тип конвертера.
     */
    public ConverterType determineTypeOfConversion(String source, String destination) {
        if ((source.equals("xml")) && (destination.equals("json"))) {
            return ConverterType.XML_2_JSON;
        } else if ((source.equals("json")) && (destination.equals("xml"))) {
            return ConverterType.JSON_2_XML;
        } else {
            return ConverterType.WRONG;
        }
    }

    /**
     * Определяет тип конвертера.
     * @param source Расширение файла, из которого считываются данные.
     * @return Тип конвертера.
     */
    public ConverterType determineTypeOfConversion(String source) {
        if (source.equals("xml")) {
            return ConverterType.XML_2_JSON;
        } else if (source.equals("json")) {
            return ConverterType.JSON_2_XML;
        } else {
            return ConverterType.WRONG;
        }
    }
}

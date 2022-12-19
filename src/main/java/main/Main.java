package main;

import factory.Converter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import validator.InputValidator;

import javax.xml.bind.JAXBException;

/**
 * Запускающий класс.
 */
@Slf4j
public class Main {
    public static void main(@NonNull String[] args) throws Exception {
        log.info("Программа запущена.");
        try {
            new Converter(new InputValidator(args)).convert();
        } catch (JAXBException exception) {
            val message = "Невозможно создать экземпляр без аргументов у какого-то класса из пакета model.";
            System.out.println(message);
            throw new JAXBException(message);
        }
        log.info("Программа завершена.");
        System.out.println("Конвертация прошла успешно.");
    }
}

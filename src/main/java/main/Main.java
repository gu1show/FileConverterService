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
        log.info("Конвертация запущена.");
        try {
            log.info("Начинается валидация.");
            InputValidator validator = new InputValidator(args);
            validator.validate();
            log.info("Валидация завершена.");

            new Converter(validator).convert();
        } catch (JAXBException exception) {
            val message = "Невозможно создать экземпляр без аргументов у какого-то класса из пакета model.";
            System.out.println(message);
        }
        log.info("Конвертация завершена.");
        System.out.println("Конвертация прошла успешно.");
    }
}

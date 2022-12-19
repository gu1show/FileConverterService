package main;

import factory.Converter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import validator.InputValidator;

/**
 * Запускающий класс.
 */
@Slf4j
public class Main {
    public static void main(@NonNull String[] args) throws Exception {
        log.info("Программа запущена.");
        new Converter(new InputValidator(args)).convert();
        log.info("Программа завершена.");
        System.out.println("Конвертация прошла успешно.");
    }
}

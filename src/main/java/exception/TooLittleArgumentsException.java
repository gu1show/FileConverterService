package exception;

/**
 * Сигнализирует о том, что на входе менее двух аргументов.
 */
public class TooLittleArgumentsException extends Exception {
    /**
     * Конструирует исключение с null в качестве подробного сообщения об ошибке.
     */
    public TooLittleArgumentsException() {
        super();
    }

    /**
     * Конструирует исключение с определённым сообщением об ошибке.
     * @param errorMessage Определённое сообщение.
     */
    public TooLittleArgumentsException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Конструирует исключение с указанной причиной.
     * @param cause Причина ошибки.
     */
    public TooLittleArgumentsException(Throwable cause) {
        super(cause);
    }

    /**
     * Конструирует исключение с определённым сообщением и с указанной причиной.
     * @param errorMessage Определённое сообщение.
     * @param cause Причина ошибки.
     */
    public TooLittleArgumentsException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

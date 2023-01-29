package exception;

/**
 * Сигнализирует о том, что на входе менее двух аргументов.
 */
public class TooFewArgumentsException extends Exception {
    /**
     * Конструирует исключение с null в качестве подробного сообщения об ошибке.
     */
    public TooFewArgumentsException() {
        super();
    }

    /**
     * Конструирует исключение с определённым сообщением об ошибке.
     * @param errorMessage Определённое сообщение.
     */
    public TooFewArgumentsException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Конструирует исключение с указанной причиной.
     * @param cause Причина ошибки.
     */
    public TooFewArgumentsException(Throwable cause) {
        super(cause);
    }

    /**
     * Конструирует исключение с определённым сообщением и с указанной причиной.
     * @param errorMessage Определённое сообщение.
     * @param cause Причина ошибки.
     */
    public TooFewArgumentsException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}

package org.example.swapi.exceptions;

/**
 * Исключение, указывающее на некорректный ответ от API.
 */
public class InvalidResponseException extends RuntimeException {

    /**
     * Создаёт экземпляр InvalidResponseException.
     *
     * @param message сообщение об ошибке
     */
    public InvalidResponseException(String message) {
        super(message);
    }
}
package org.example.swapi.exceptions;

/**
 * Исключение, указывающее на ошибку взаимодействия с API.
 */
public class ApiException extends RuntimeException {

    /**
     * Создаёт экземпляр ApiException.
     *
     * @param message сообщение об ошибке
     * @param cause причина ошибки
     */
    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
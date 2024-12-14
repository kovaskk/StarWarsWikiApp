package org.example.swapi.exceptions;

/**
 * Исключение, указывающее на отсутствие искомого объекта.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Создаёт экземпляр NotFoundException.
     *
     * @param message сообщение об ошибке
     */
    public NotFoundException(String message) {
        super(message);
    }
}
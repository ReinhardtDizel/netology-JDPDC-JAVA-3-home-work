package ru.netology.exceptions.EnlargeException;

public class EnlargeException extends RuntimeException {

    public EnlargeException(String message) {
        super(message);
    }

    public EnlargeException(String message, Throwable cause) {
        super(message, cause);
    }
}

package ru.kpfu.itis.exceptions;

public class NotValidPasswordException extends Exception {

    public NotValidPasswordException() {
        super();
    }

    public NotValidPasswordException(String message) {
        super(message);
    }

    public NotValidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidPasswordException(Throwable cause) {
        super(cause);
    }
}

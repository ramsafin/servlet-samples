package ru.kpfu.itis.exceptions;

public class UserDuplicateException extends Exception {

    public UserDuplicateException() {
        super();
    }

    public UserDuplicateException(String message) {
        super(message);
    }

    public UserDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDuplicateException(Throwable cause) {
        super(cause);
    }
}

package ru.kpfu.itis.exceptions;

public class IdentifyException extends Exception {

    public IdentifyException() {
        super();
    }

    public IdentifyException(String message) {
        super(message);
    }

    public IdentifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentifyException(Throwable cause) {
        super(cause);
    }

    protected IdentifyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

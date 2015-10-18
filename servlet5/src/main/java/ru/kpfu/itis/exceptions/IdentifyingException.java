package ru.kpfu.itis.exceptions;

public class IdentifyingException extends Exception{

    public IdentifyingException() {
        super();
    }

    public IdentifyingException(String message) {
        super(message);
    }

    public IdentifyingException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentifyingException(Throwable cause) {
        super(cause);
    }

    protected IdentifyingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

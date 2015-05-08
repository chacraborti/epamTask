package com.epam.sidarovich.exception;

/**
 * Created by ilona on 12.04.15.
 */
public class LogicException extends Exception {
    public LogicException() {
        super();
    }

    public LogicException(String message) {
        super(message);
    }

    public LogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public LogicException(Throwable cause) {
        super(cause);
    }


}

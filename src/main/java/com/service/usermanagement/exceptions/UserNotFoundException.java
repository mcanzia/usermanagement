package com.service.usermanagement.exceptions;

/**
 * Generic exception thrown when requested User is not found in database
 * @author Michael Canziani
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Generic exception thrown when requested User is not found in database
     */
    public UserNotFoundException() {
    }

    /**
     * Constructor for UserNotFoundException if provided message parameter
     * @param message message to be displayed on exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor for UserNotFoundException if provided throwable parameter
     * @param cause throwable cause of exception
     */
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for UserNotFoundException if provided message parameter and throwable parameter
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for UserNotFoundException if provided additional parameters
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     * @param enableSuppression if able to enable suppression for exception
     * @param writableStackTrace if stack trace is writable for exception
     */
    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

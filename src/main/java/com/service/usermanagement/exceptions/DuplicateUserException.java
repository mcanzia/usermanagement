package com.service.usermanagement.exceptions;

/**
 * Generic exception thrown when a duplicate User is created
 * @author Michael Canziani
 */
public class DuplicateUserException extends Exception {

    /**
     * Generic exception thrown when a duplicate User is created
     * Users should have unique id and email fields
     */
    public DuplicateUserException() {
    }

    /**
     * Constructor for DuplicateUserException if provided message parameter
     * @param message message to be displayed on exception
     */
    public DuplicateUserException(String message) {
        super(message);
    }

    /**
     * Constructor for DuplicateUserException if provided throwable parameter
     * @param cause throwable cause of exception
     */
    public DuplicateUserException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for DuplicateUserException if provided message parameter and throwable parameter
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     */
    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for DuplicateUserException if provided additional parameters
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     * @param enableSuppression if able to enable suppression for exception
     * @param writableStackTrace if stack trace is writable for exception
     */
    public DuplicateUserException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.service.usermanagement.exceptions;

/**
 * Generic exception thrown when a duplicate group is created
 * @author Michael Canziani
 */
public class DuplicateGroupException extends Exception {

    /**
     * Generic exception thrown when a duplicate group is created
     * Groups should have unique id and name fields
     */
    public DuplicateGroupException() {
    }

    /**
     * Constructor for DuplicateGroupException if provided message parameter
     * @param message message to be displayed on exception
     */
    public DuplicateGroupException(String message) {
        super(message);
    }

    /**
     * Constructor for DuplicateGroupException if provided throwable parameter
     * @param cause throwable cause of exception
     */
    public DuplicateGroupException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for DuplicateGroupException if provided message parameter and throwable parameter
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     */
    public DuplicateGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for DuplicateGroupException if provided additional parameters
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     * @param enableSuppression if able to enable suppression for exception
     * @param writableStackTrace if stack trace is writable for exception
     */
    public DuplicateGroupException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

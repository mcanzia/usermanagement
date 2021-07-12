package com.service.usermanagement.exceptions;

/**
 * Generic exception thrown when requested Group is not found in database
 * @author Michael Canziani
 */
public class GroupNotFoundException extends RuntimeException {

    /**
     * Generic exception thrown when requested Group is not found in database
     */
    public GroupNotFoundException() {
    }

    /**
     * Constructor for GroupNotFoundException if provided message parameter
     * @param message message to be displayed on exception
     */
    public GroupNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor for GroupNotFoundException if provided throwable parameter
     * @param cause throwable cause of exception
     */
    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for GroupNotFoundException if provided message parameter and throwable parameter
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     */
    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for GroupNotFoundException if provided additional parameters
     * @param message message to be displayed on exception
     * @param cause throwable cause of exception
     * @param enableSuppression if able to enable suppression for exception
     * @param writableStackTrace if stack trace is writable for exception
     */
    public GroupNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.globallogic.users.exception;

public enum ExceptionMessages {
    USER_NOT_FOUND("Grupo no encontrado");
   

    private final String message;

    /**
     * Constructor for {@code ExceptionMessages} class.
     * 
     * @param message The correspondent message for the exception type
     */
    private ExceptionMessages(final String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

}

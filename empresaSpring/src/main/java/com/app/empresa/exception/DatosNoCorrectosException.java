package com.app.empresa.exception;

/**
 * Excepción personalizada utilizada para manejar errores relacionados con datos incorrectos.
 * Esta excepción se lanza cuando los datos proporcionados no cumplen con los requisitos esperados.
 */
public class DatosNoCorrectosException extends Exception {

    private int errorCode;

    // Constructor sin mensaje
    public DatosNoCorrectosException() {
        super();
    }

    // Constructor con mensaje
    public DatosNoCorrectosException(String message) {
        super(message);
    }

    // Constructor con mensaje y causa
    public DatosNoCorrectosException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor con mensaje y código de error
    public DatosNoCorrectosException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    // Obtener código de error
    public int getErrorCode() {
        return errorCode;
    }
}

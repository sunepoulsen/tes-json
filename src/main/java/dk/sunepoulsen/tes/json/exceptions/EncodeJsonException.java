package dk.sunepoulsen.tes.json.exceptions;

/**
 * Exception to be thrown by <code>JsonMapper</code> in case of errors
 * while encoding an object to a JSON string.
 *
 * @see dk.sunepoulsen.tes.json.JsonMapper
 */
public class EncodeJsonException extends RuntimeException {
    public EncodeJsonException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

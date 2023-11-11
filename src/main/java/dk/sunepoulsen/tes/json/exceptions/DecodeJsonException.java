package dk.sunepoulsen.tes.json.exceptions;

/**
 * Exception to be thrown by <code>JsonMapper</code> in case of errors
 * while decoding a JSON string.
 *
 * @see dk.sunepoulsen.tes.json.JsonMapper
 */
public class DecodeJsonException extends RuntimeException {
    public DecodeJsonException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

package dk.sunepoulsen.tes.json.exceptions;

public class EncodeJsonException extends RuntimeException {
    public EncodeJsonException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

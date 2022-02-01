package dk.sunepoulsen.tes.json.exceptions;

public class DecodeJsonException extends RuntimeException {
    public DecodeJsonException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

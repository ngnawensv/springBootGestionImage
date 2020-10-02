package cm.belrose.springBootGestionImage.exceptions;

import org.springframework.http.HttpStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class SpringBootGestionImageException extends Exception {

    private HttpStatus status;

    public SpringBootGestionImageException() {
        super();
    }

    public SpringBootGestionImageException(String message) {
        super(message);
    }

    public SpringBootGestionImageException(String message, Throwable cause) {
        super(message, cause);
    }
    public SpringBootGestionImageException(Throwable cause) {
        super(cause);
    }
    protected SpringBootGestionImageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public SpringBootGestionImageException(String message, HttpStatus status) {
        super(message);
        this.status=status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

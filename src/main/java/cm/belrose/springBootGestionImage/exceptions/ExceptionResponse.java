package cm.belrose.springBootGestionImage.exceptions;

import org.springframework.http.HttpStatus;
/**
 * @author Ngnawen Samuel
 * This class represent the POJO that we return to the user that the exception/error occur
 */
public class ExceptionResponse {

    private String currentDate;
    private String errorMessage;
    private int errorCode;
    private HttpStatus status;
    private String url;

    public ExceptionResponse() {
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

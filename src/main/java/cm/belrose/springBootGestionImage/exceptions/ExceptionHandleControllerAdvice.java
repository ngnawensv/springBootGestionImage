package cm.belrose.springBootGestionImage.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ngnawen Samuel
 *
 */
@RestControllerAdvice
public class ExceptionHandleControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandleControllerAdvice.class);

    @ExceptionHandler(SpringBootGestionImageException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFoundException(SpringBootGestionImageException exception, HttpServletRequest request)  {
       ExceptionResponse exceptionResponse = new ExceptionResponse();
        SimpleDateFormat dateForSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        exceptionResponse.setCurrentDate(dateForSimpleDateFormat.format(new Date()));
        exceptionResponse.setErrorMessage(exception.getMessage());
        exceptionResponse.setErrorCode(exception.getStatus().value());
        exceptionResponse.setStatus(exception.getStatus());
        exceptionResponse.setUrl(request.getRequestURL().toString());
        return new ResponseEntity<>(exceptionResponse,exception.getStatus());
    }

    /**
     * This method intercept all the IOException
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(IOException.class)
    //@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handlerIOException(IOException exception,HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        SimpleDateFormat dateForSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        exceptionResponse.setCurrentDate(dateForSimpleDateFormat.format(new Date()));
        exceptionResponse.setErrorMessage(exception.getMessage());
        exceptionResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setUrl(request.getRequestURL().toString());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * This method intercept all the unknow Exception
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
   // @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> unknowException(Exception exception,HttpServletRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        SimpleDateFormat dateForSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        exceptionResponse.setCurrentDate(dateForSimpleDateFormat.format(new Date()));
        exceptionResponse.setErrorMessage(exception.getMessage());
        exceptionResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setUrl(request.getRequestURL().toString());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }



}

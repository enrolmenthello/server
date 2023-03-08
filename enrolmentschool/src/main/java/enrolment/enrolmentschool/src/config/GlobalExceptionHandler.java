package enrolment.enrolmentschool.src.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.Objects.requireNonNull;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BindException.class})
    public BaseResponse<?> errorValid(BindException ex) {
        String errorCode = requireNonNull(ex.getFieldError()).getDefaultMessage();
        log.error(errorCode);
        return new BaseResponse(500, HttpStatus.BAD_REQUEST, errorCode);
    }

    @ExceptionHandler(BaseException.class)
    public BaseResponse<?> handleBaseException(BaseException exception) {
        int errorCode = exception.getCode();
        HttpStatus httpStatus = exception.getHttpStatus();
        String message = exception.getMessage();
        return new BaseResponse(errorCode, httpStatus, message);
    }
}
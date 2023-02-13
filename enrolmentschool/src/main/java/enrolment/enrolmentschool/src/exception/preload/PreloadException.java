package enrolment.enrolmentschool.src.exception.preload;

import enrolment.enrolmentschool.src.config.BaseException;
import org.springframework.http.HttpStatus;

public abstract class PreloadException extends BaseException {
    public PreloadException(int code, HttpStatus httpStatus, String message){
        super(code, httpStatus, message);
    }
}

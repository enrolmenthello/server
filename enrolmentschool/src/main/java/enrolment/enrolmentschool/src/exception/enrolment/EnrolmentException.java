package enrolment.enrolmentschool.src.exception.enrolment;

import enrolment.enrolmentschool.src.config.BaseException;
import org.springframework.http.HttpStatus;

public abstract class EnrolmentException extends BaseException {
    public EnrolmentException(int code, HttpStatus httpStatus,String message){
        super(code, httpStatus, message);
    }
}

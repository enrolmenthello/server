package enrolment.enrolmentschool.src.exception;


import enrolment.enrolmentschool.src.config.BaseException;
import org.springframework.http.HttpStatus;

public abstract class MemberException extends BaseException {
    public MemberException(int code, HttpStatus httpStatus, String message) {
        super(code, httpStatus, message);
    }
}

package enrolment.enrolmentschool.src.exception.subject;

import enrolment.enrolmentschool.src.config.BaseException;
import org.springframework.http.HttpStatus;

public abstract class SubjectException extends BaseException {
    public SubjectException(int code, HttpStatus httpStatus, String message) {
        super(code, httpStatus, message);
    }
}

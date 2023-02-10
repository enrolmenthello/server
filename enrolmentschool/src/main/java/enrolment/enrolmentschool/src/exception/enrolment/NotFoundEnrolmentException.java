package enrolment.enrolmentschool.src.exception.enrolment;

import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;

public class NotFoundEnrolmentException extends EnrolmentException {

    public NotFoundEnrolmentException(){
        super(EnrolmentExceptionStatus.NOT_FOUND_ENROLMENT.getCode(),
                EnrolmentExceptionStatus.NOT_FOUND_ENROLMENT.getHttpStatus(),
                EnrolmentExceptionStatus.NOT_FOUND_ENROLMENT.getMessage());
    }
}

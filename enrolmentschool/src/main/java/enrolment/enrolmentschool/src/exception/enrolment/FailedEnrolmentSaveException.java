package enrolment.enrolmentschool.src.exception.enrolment;

import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;
import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentExceptionStatus;

public class FailedEnrolmentSaveException extends EnrolmentException {
    public FailedEnrolmentSaveException(){
        super(EnrolmentExceptionStatus.FAILED_ENROLMENT_SAVE.getCode(),
                EnrolmentExceptionStatus.FAILED_ENROLMENT_SAVE.getHttpStatus(),
                EnrolmentExceptionStatus.FAILED_ENROLMENT_SAVE.getMessage());
    }
}

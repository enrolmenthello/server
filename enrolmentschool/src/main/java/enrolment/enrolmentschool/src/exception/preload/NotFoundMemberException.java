package enrolment.enrolmentschool.src.exception.preload;

import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;
import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentExceptionStatus;

public class NotFoundMemberException extends PreloadException {
    public NotFoundMemberException(){
        super(PreloadExceptionStatus.NOT_FOUND_MEMBER.getCode(),
                PreloadExceptionStatus.NOT_FOUND_MEMBER.getHttpStatus(),
                PreloadExceptionStatus.NOT_FOUND_MEMBER.getMessage());
    }
}

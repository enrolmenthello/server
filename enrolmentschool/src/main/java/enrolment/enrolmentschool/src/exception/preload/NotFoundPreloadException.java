package enrolment.enrolmentschool.src.exception.preload;

import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;
import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentExceptionStatus;

public class NotFoundPreloadException extends PreloadException {

    public NotFoundPreloadException(){
        super(PreloadExceptionStatus.NOT_FOUND_PRELOAD.getCode(),
                PreloadExceptionStatus.NOT_FOUND_PRELOAD.getHttpStatus(),
                PreloadExceptionStatus.NOT_FOUND_PRELOAD.getMessage());
    }
}

package enrolment.enrolmentschool.src.exception.preload;


import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;
import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentExceptionStatus;

public class FailedPreloadSaveException extends PreloadException {
    public FailedPreloadSaveException(){
        super(PreloadExceptionStatus.FAILED_PRELOAD_SAVE.getCode(),
                PreloadExceptionStatus.FAILED_PRELOAD_SAVE.getHttpStatus(),
                PreloadExceptionStatus.FAILED_PRELOAD_SAVE.getMessage());
    }
}

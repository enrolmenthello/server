package enrolment.enrolmentschool.src.exception.subject;

public class NotFoundSubjectException extends SubjectException {
    public NotFoundSubjectException() {
        super(SubjectExceptionStatus.NOT_FOUND_SUBJECT.getCode(),
                SubjectExceptionStatus.NOT_FOUND_SUBJECT.getHttpStatus(),
                SubjectExceptionStatus.NOT_FOUND_SUBJECT.getMessage());
    }
}

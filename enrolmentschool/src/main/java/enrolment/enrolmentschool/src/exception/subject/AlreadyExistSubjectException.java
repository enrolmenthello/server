package enrolment.enrolmentschool.src.exception.subject;

public class AlreadyExistSubjectException extends SubjectException{
    public AlreadyExistSubjectException(){
        super(SubjectExceptionStatus.ALREADY_EXIST_SUBJECT.getCode(),
                SubjectExceptionStatus.ALREADY_EXIST_SUBJECT.getHttpStatus(),
                SubjectExceptionStatus.ALREADY_EXIST_SUBJECT.getMessage());
    }
}

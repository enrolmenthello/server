package enrolment.enrolmentschool.src.exception.subject;

public class AlreadyExist2SubjectException extends SubjectException{
    public AlreadyExist2SubjectException(){
        super(SubjectExceptionStatus.ALREADY_EXIST_SUBJECT.getCode(),
                SubjectExceptionStatus.ALREADY_EXIST_SUBJECT.getHttpStatus(),
                SubjectExceptionStatus.ALREADY_EXIST_SUBJECT.getMessage());
    }
}

package enrolment.enrolmentschool.src.exception.enrolment;

public class NotFoundMemberException extends EnrolmentException{
    public NotFoundMemberException(){
        super(EnrolmentExceptionStatus.NOT_FOUND_MEMBER.getCode(),
                EnrolmentExceptionStatus.NOT_FOUND_MEMBER.getHttpStatus(),
                EnrolmentExceptionStatus.NOT_FOUND_MEMBER.getMessage());
    }
}

package enrolment.enrolmentschool.src.exception;

public class NotFoundMemberException extends MemberException{
    public NotFoundMemberException() {
        super(MemberExceptionStatus.NOT_FOUND_STAFF.getCode(),
                MemberExceptionStatus.NOT_FOUND_STAFF.getHttpStatus(),
                MemberExceptionStatus.NOT_FOUND_STAFF.getMessage());
    }
}
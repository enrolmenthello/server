package enrolment.enrolmentschool.src.exception.enrolment;

public class WrongJoinMemberException extends EnrolmentException{
    public WrongJoinMemberException(){
        super(EnrolmentExceptionStatus.WRONG_JOIN_MEMBER.getCode(),
                EnrolmentExceptionStatus.WRONG_JOIN_MEMBER.getHttpStatus(),
                EnrolmentExceptionStatus.WRONG_JOIN_MEMBER.getMessage());
    }
}

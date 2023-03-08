package enrolment.enrolmentschool.src.exception.member;

public class AlreadyExistMemberException extends MemberException{
    public AlreadyExistMemberException(){
        super(MemberExceptionStatus.ALEADY_EXIST_MEMBER.getCode(),
                MemberExceptionStatus.ALEADY_EXIST_MEMBER.getHttpStatus(),
                MemberExceptionStatus.ALEADY_EXIST_MEMBER.getMessage());
    }
}

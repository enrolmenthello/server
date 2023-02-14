package enrolment.enrolmentschool.src.exception.member;

public class NotJoinMemberException extends MemberException{
    public NotJoinMemberException() {
        super(MemberExceptionStatus. WRONG_JOIN_MEMBER.getCode(),
                MemberExceptionStatus. WRONG_JOIN_MEMBER.getHttpStatus(),
                MemberExceptionStatus. WRONG_JOIN_MEMBER.getMessage());
    }
}

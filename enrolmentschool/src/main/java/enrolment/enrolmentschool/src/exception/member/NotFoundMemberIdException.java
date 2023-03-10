package enrolment.enrolmentschool.src.exception.member;

import enrolment.enrolmentschool.src.exception.member.MemberException;
import enrolment.enrolmentschool.src.exception.member.MemberExceptionStatus;

public class NotFoundMemberIdException extends MemberException {
    public NotFoundMemberIdException() {
        super(MemberExceptionStatus.NOT_FOUND_MEMBER_ID.getCode(),
                MemberExceptionStatus.NOT_FOUND_MEMBER_ID.getHttpStatus(),
                MemberExceptionStatus.NOT_FOUND_MEMBER_ID.getMessage());
    }
}
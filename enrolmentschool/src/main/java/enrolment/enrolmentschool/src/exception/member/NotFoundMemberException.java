package enrolment.enrolmentschool.src.exception.member;

import enrolment.enrolmentschool.src.exception.member.MemberException;
import enrolment.enrolmentschool.src.exception.member.MemberExceptionStatus;

public class NotFoundMemberException extends MemberException {
    public NotFoundMemberException() {
        super(MemberExceptionStatus.NOT_FOUND_STAFF.getCode(),
                MemberExceptionStatus.NOT_FOUND_STAFF.getHttpStatus(),
                MemberExceptionStatus.NOT_FOUND_STAFF.getMessage());
    }
}
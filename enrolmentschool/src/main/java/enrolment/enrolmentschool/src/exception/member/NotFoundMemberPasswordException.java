package enrolment.enrolmentschool.src.exception.member;

import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;
import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentExceptionStatus;

public class NotFoundMemberPasswordException extends MemberException {
    public NotFoundMemberPasswordException(){
        super(MemberExceptionStatus.NOT_FOUND_MEMBER_PASSWORD.getCode(),
                MemberExceptionStatus.NOT_FOUND_MEMBER_PASSWORD.getHttpStatus(),
                MemberExceptionStatus.NOT_FOUND_MEMBER_PASSWORD.getMessage());
    }
}

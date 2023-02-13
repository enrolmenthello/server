package enrolment.enrolmentschool.src.exception.preload;

import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentException;
import enrolment.enrolmentschool.src.exception.enrolment.EnrolmentExceptionStatus;

public class WrongJoinMemberException extends PreloadException {
    public WrongJoinMemberException(){
        super(PreloadExceptionStatus.WRONG_JOIN_MEMBER.getCode(),
                PreloadExceptionStatus.WRONG_JOIN_MEMBER.getHttpStatus(),
                PreloadExceptionStatus.WRONG_JOIN_MEMBER.getMessage());
    }
}

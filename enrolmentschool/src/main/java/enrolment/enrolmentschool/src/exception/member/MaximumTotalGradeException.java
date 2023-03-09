package enrolment.enrolmentschool.src.exception.member;

public class MaximumTotalGradeException extends MemberException{
    public MaximumTotalGradeException(){
        super(MemberExceptionStatus.MAXIMUM_TOTAL_GRADE.getCode(),
                MemberExceptionStatus.MAXIMUM_TOTAL_GRADE.getHttpStatus(),
                MemberExceptionStatus.MAXIMUM_TOTAL_GRADE.getMessage());
    }
}

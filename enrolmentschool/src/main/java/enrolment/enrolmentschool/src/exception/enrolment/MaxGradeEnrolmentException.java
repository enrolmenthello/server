package enrolment.enrolmentschool.src.exception.enrolment;

public class MaxGradeEnrolmentException extends EnrolmentException {
    public MaxGradeEnrolmentException(){
        super(EnrolmentExceptionStatus.MAX_ENROLMENT_GRADE.getCode(),
                EnrolmentExceptionStatus.MAX_ENROLMENT_GRADE.getHttpStatus(),
                EnrolmentExceptionStatus.MAX_ENROLMENT_GRADE.getMessage());
    }
}

package enrolment.enrolmentschool.src.exception.subject;

public class LimitSubject2StockQuantityException extends SubjectException{
    public LimitSubject2StockQuantityException(){
        super(SubjectExceptionStatus.LIMIT_STOCK2_QUANTITY.getCode(),
                SubjectExceptionStatus.LIMIT_STOCK2_QUANTITY.getHttpStatus(),
                SubjectExceptionStatus.LIMIT_STOCK2_QUANTITY.getMessage()
                );
    }
}

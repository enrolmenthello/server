package enrolment.enrolmentschool.src.exception.subject;

public class LimitSubjectStockQuantityException extends SubjectException{
    public LimitSubjectStockQuantityException(){
        super(SubjectExceptionStatus.LIMIT_STOCK_QUANTITY.getCode(),
                SubjectExceptionStatus.LIMIT_STOCK_QUANTITY.getHttpStatus(),
                SubjectExceptionStatus.LIMIT_STOCK_QUANTITY.getMessage()
                );
    }
}

package enrolment.enrolmentschool.src.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseException extends RuntimeException{

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;

    public BaseException(int code, HttpStatus httpStatus, String message){
//        super(message);
        this.message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }

}
package enrolment.enrolmentschool.src.exception.subject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SubjectExceptionStatus {
    NOT_FOUND_SUBJECT(3000,HttpStatus.BAD_REQUEST, "등록된 과목이 없습니다. 다시 시도해주세요."),
    WRONG_JOIN_SUBJECT(3001, HttpStatus.BAD_REQUEST, "과목 등록에 실패하였습니다. 다시 시도해주세요."),
    WRONG_TYPE_INPUT(3002, HttpStatus.BAD_REQUEST, "아이디는 숫자만 입력해 주세요.");

    private final int code;
    private final org.springframework.http.HttpStatus httpStatus;
    private final String message;
}


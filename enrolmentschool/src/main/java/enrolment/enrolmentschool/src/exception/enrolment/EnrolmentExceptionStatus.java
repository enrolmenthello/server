package enrolment.enrolmentschool.src.exception.enrolment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EnrolmentExceptionStatus {
    // contract
    NOT_FOUND_ENROLMENT(2000,HttpStatus.BAD_REQUEST, "존재하지 않는 과목입니다."),
    MAX_ENROLMENT_GRADE(2005,HttpStatus.BAD_REQUEST,"수강신청 학점은 18학점을 초과할 수 없습니다."),

    // customer
    NOT_FOUND_MEMBER(2001, HttpStatus.BAD_REQUEST, "등록된 고객 정보가 없습니다. 다시 시도해주시거나 회원 가입 후 진행해주세요."),
    WRONG_JOIN_MEMBER(2002, HttpStatus.BAD_REQUEST, "가입에 실패하였습니다. 다시 시도해주세요."),
    WRONG_TYPE_INPUT(2003, HttpStatus.BAD_REQUEST, "아이디는 숫자만 입력해 주세요."),

    FAILED_ENROLMENT_SAVE(2004, HttpStatus.BAD_REQUEST, "예기치 못한 오류로 수강신청에 실패하였습니다. 수강신청을 다시 시도해주세요.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}

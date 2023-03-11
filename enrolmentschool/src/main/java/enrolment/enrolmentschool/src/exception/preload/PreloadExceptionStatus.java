package enrolment.enrolmentschool.src.exception.preload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PreloadExceptionStatus {
    // contract
    NOT_FOUND_PRELOAD(2000, HttpStatus.BAD_REQUEST, "해당 학생의 취소하려는 과목의 미리담기 현황을 확인할 수 없습니다.."),

    // customer
    NOT_FOUND_MEMBER(2001, HttpStatus.BAD_REQUEST, "등록된 고객 정보가 없습니다. 다시 시도해주시거나 회원 가입 후 진행해주세요."),
    WRONG_JOIN_MEMBER(2002, HttpStatus.BAD_REQUEST, "가입에 실패하였습니다. 다시 시도해주세요."),
    WRONG_TYPE_INPUT(2003, HttpStatus.BAD_REQUEST, "아이디는 숫자만 입력해 주세요."),

    FAILED_PRELOAD_SAVE(2004, HttpStatus.BAD_REQUEST, "예기치 못한 오류로 미리담기에 실패하였습니다. 미리담기를 다시 시도해주세요.");

    private final int code;
    private final HttpStatus httpStatus;
    private final String message;
}

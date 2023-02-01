package enrolment.enrolmentschool.src.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostMemberResponse {
    private String message;

    private Long memberId;
    private String memberName;
    private String password;

}
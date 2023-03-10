package enrolment.enrolmentschool.src.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostMemberLoginResponse {
    private String id;

    private String password;

    private String message;
}

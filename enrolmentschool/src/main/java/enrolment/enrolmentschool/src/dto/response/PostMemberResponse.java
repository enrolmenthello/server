package enrolment.enrolmentschool.src.dto.response;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class PostMemberResponse {
    private String id;

    private String password;

    private String name;
    private String message;

}
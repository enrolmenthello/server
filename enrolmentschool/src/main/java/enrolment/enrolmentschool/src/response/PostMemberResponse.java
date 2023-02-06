package enrolment.enrolmentschool.src.response;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class PostMemberResponse {
    private Long id;

    private String password;

    private String name;

}
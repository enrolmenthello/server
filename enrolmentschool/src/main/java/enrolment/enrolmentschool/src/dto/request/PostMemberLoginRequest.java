package enrolment.enrolmentschool.src.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberLoginRequest {
    @NotNull(message="학생 학번을 입력해주세요.")
    private String id;

    @NotEmpty(message="학생 비밀번호를 입력해주세요.")
    private String password;


}

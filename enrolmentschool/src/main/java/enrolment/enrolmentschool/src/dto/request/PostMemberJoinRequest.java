package enrolment.enrolmentschool.src.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostMemberJoinRequest {
    @NotNull(message="학번을 입력해주세요.")
    private Long id;

    @NotEmpty(message="비밀 번호를 입력해주세요.")
    private String password;

    @NotEmpty(message="이름을 입력해주세요.")
    private String name;



}

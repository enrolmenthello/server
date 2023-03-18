package enrolment.enrolmentschool.src.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import enrolment.enrolmentschool.src.domain.Member;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostMemberJoinRequest {
    @NotEmpty(message="학번을 입력해주세요.")
    private String id;

    @NotEmpty(message="비밀 번호를 입력해주세요.")
    private String password;

    @NotEmpty(message="이름을 입력해주세요.")
    private String name;

    public Member toEntity(){
        return Member.builder()
                .id(this.id)
                .name(this.name)
                .password(this.password)
                .build();
    }

    @Builder
    public PostMemberJoinRequest(String id,String password, String name){
        this.id=id;
        this.name=name;
        this.password=password;
    }



}

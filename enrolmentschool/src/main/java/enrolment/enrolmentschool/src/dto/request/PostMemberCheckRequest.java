package enrolment.enrolmentschool.src.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostMemberCheckRequest {
    @NotEmpty(message="학번을 입력해주세요.")
    private String id;

}

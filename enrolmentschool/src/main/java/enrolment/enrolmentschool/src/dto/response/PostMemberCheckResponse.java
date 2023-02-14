package enrolment.enrolmentschool.src.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMemberCheckResponse {
    private String message;
    private boolean is;

}

package enrolment.enrolmentschool.src.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEnrolmentResponse {
    private String message;
    private Long memberId;
}

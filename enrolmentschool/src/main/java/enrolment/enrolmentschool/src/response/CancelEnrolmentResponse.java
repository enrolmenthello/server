package enrolment.enrolmentschool.src.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelEnrolmentResponse {
    private String message;
    private Long enrolmentId;
}

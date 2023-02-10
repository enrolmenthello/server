package enrolment.enrolmentschool.src.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelPreloadResponse {
    private String message;
    private Long preloadId;
}

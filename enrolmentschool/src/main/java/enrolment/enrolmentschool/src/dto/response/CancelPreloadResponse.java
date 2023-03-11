package enrolment.enrolmentschool.src.dto.response;

import lombok.*;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelPreloadResponse {

    private String message;

    private String professor;
    private int gradePoint;
    private String name;
    private LocalTime time;
}

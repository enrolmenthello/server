package enrolment.enrolmentschool.src.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancelEnrolmentResponse {
    private String message;

    private String professor;
    private int gradePoint;
    private String name;
    private LocalTime time;

}

package enrolment.enrolmentschool.src.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
@AllArgsConstructor
public class GetSubjectResponse {
    private Long id;
    private String name;

    private String professor;
    private LocalTime time;
    private int gradePoint;
    private int stockQuantity;
}

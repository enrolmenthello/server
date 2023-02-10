package enrolment.enrolmentschool.src.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetSubjectResponse {
    private Long id;
    private String name;

    private String professor;
    private int time;
    private int gradePoint;
    private int stockQuantity;
}

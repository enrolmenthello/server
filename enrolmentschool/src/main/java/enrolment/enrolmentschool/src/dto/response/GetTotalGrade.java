package enrolment.enrolmentschool.src.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class GetTotalGrade {
    private Long totalGrade;
}

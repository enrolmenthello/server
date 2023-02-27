package enrolment.enrolmentschool.src.dto.response;

import enrolment.enrolmentschool.src.domain.Enrolment;
import lombok.*;
import org.springframework.context.annotation.Scope;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetEnrolmentListResponse {



    private Long subjectId;
    private String subjectName;

    private String subjectProfessor;
    private LocalTime subjectTime;
    private int subjectGradePoint;
    private int subjectStockQuantity;

    public static GetEnrolmentListResponse of(Enrolment enrolment){
        return GetEnrolmentListResponse.builder()
                .subjectId(enrolment.getSubject().getId())
                .subjectName(enrolment.getSubject().getName())
                .subjectProfessor(enrolment.getSubject().getProfessor())
                .subjectTime(enrolment.getSubject().getTime())
                .subjectGradePoint(enrolment.getSubject().getGradePoint())
                .subjectStockQuantity(enrolment.getSubject().getStockQuantity())
                .build();
    }
}

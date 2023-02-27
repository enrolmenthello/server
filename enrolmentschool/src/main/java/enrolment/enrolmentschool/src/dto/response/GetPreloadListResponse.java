package enrolment.enrolmentschool.src.dto.response;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Preload;
import lombok.*;

import java.time.LocalTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPreloadListResponse {



    private Long subjectId;
    private String subjectName;

    private String subjectProfessor;
    private LocalTime subjectTime;
    private int subjectGradePoint;
    private int subjectStockQuantity;

    public static GetPreloadListResponse of(Preload preload){
        return GetPreloadListResponse.builder()
                .subjectId(preload.getSubject().getId())
                .subjectName(preload.getSubject().getName())
                .subjectProfessor(preload.getSubject().getProfessor())
                .subjectTime(preload.getSubject().getTime())
                .subjectGradePoint(preload.getSubject().getGradePoint())
                .subjectStockQuantity(preload.getSubject().getStockQuantity())
                .build();
    }
}

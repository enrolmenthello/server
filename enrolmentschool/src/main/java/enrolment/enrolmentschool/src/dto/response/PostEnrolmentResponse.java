package enrolment.enrolmentschool.src.dto.response;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Subject;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostEnrolmentResponse {
    @ApiModelProperty(example = "수강신청이 완료되었습니다.")
    private String message;
    private Long enrolmentId;

    private String name;

    private String professor;
    private LocalTime time;
    private int gradePoint;
    private int totalGrade;

//    public static PostEnrolmentResponse of(Subject subject){
//        return PostEnrolmentResponse.builder()
//                .subjectId(subject.getId())
//                .name(subject.getName())
//                .stockQuantity(subject.getStockQuantity())
//                .gradePoint(subject.getGradePoint())
//                .professor(subject.getProfessor())
//                .time(subject.getTime())
//                .build();
//    }

}


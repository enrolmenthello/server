package enrolment.enrolmentschool.src.dto.response;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostSubjectResponse {
    private Long subjectId;
    private String subjectName;

    private String subjectProfessor;
    private int subjectTime;
    private int enrolmentGrade;
    private int stockQuantity;
}

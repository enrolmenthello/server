package enrolment.enrolmentschool.src.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEnrolmentResponse {
    @ApiModelProperty(notes = "과목ID", example = "0")
    private String subjectId;

    @ApiModelProperty(notes = "과목이름", example = "0")
    private String subjectName;

    @ApiModelProperty(notes = "과목교수", example = "0")
    private String subjectProfessor;

    @ApiModelProperty(notes = "과목시간", example = "0")
    private int subjectTime;

    @ApiModelProperty(notes = "과목학점", example = "0")
    private int enrolmentGrade;
}

package enrolment.enrolmentschool.src.dto.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostEnrolmentRequest {

    @ApiParam(value="강좌번호",example = "0")
    @ApiModelProperty(example = "123")
    private String subjectId;

    @ApiParam(value="과목이름",example = "0")
    @ApiModelProperty(example = "팀프로젝트")
    private String subjectName;

    @ApiParam(value="교수",example = "0")
    @ApiModelProperty(example = "최성훈")
    private String subjectProfessor;

    @ApiParam(value="과목 시간",example = "0")
    @ApiModelProperty(example = "09:00~12:00")
    private String subjectTime;

    @ApiParam(value="과목 학점",example = "0")
    @ApiModelProperty(example = "3")
    private String enrolmentGrade;













}

package enrolment.enrolmentschool.src.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class PostEnrolmentGradeRequest {

    @ApiParam(value="학생 id",example = "0")
    @ApiModelProperty(example = "0")
    private String memberId;
}

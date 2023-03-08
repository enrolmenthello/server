package enrolment.enrolmentschool.src.dto.response;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class PostSubjectResponse {
    @ApiParam(value="과목번호", example = "0")
    @ApiModelProperty(example = "12345678")
    private Long subjectId;

}

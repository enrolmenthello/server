package enrolment.enrolmentschool.src.dto.request;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostPreloadCancelRequest {
    @ApiParam(value="학번", example = "0")
    @ApiModelProperty(example = "12345678")
    private String memberId;

    @ApiParam(value="강좌번호", example = "0")
    @ApiModelProperty(example = "1234")
    private Long subjectId;
}

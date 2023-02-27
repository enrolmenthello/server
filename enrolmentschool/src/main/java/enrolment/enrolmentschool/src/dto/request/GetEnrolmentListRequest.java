package enrolment.enrolmentschool.src.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetEnrolmentListRequest {
    @ApiParam(value="학번",example = "12345678")
    private String memberId;
}

package enrolment.enrolmentschool.src.dto.request;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostEnrolmentCancelRequest {
    @ApiParam(value="수강신청 id", example = "12345678")
    private Long enrolmentId;
}

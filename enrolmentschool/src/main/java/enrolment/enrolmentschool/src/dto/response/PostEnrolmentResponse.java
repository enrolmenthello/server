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
public class PostEnrolmentResponse {
    @ApiModelProperty(notes = "과목ID", example = "0")
    private Long enrolmentId;
}


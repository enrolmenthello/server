package enrolment.enrolmentschool.src.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
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
    private int stockQuantity;

}


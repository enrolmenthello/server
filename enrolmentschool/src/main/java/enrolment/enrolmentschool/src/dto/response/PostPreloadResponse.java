package enrolment.enrolmentschool.src.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPreloadResponse {
    @ApiModelProperty(example = "--님의 미리담기 현황")
    private String message;
    private Long preloadId;

    private String name;

    private String professor;
    private LocalTime time;
    private int gradePoint;
    private int stockQuantity;

}

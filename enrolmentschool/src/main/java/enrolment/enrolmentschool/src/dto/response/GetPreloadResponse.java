package enrolment.enrolmentschool.src.dto.response;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Preload;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetPreloadResponse {
    @ApiModelProperty(example = "--님의 미리담기 현황")
    private String message;

    private Long preloadId;
    private Long memberId;
    private Long subjectId;
    private String subjectName;

    public static GetPreloadResponse of(Preload preload){
        return GetPreloadResponse.builder()
                .message(preload.getMember().getName()+"님의 미리담기 현황")
                .preloadId(preload.getEnrolmentId())
                .subjectId(preload.getSubject().getId())
                .subjectName(preload.getSubject().getName())
                .memberId(preload.getMember().getId())
                .build();
    }
}

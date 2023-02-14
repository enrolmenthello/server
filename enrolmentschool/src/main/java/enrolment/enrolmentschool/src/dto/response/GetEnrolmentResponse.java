package enrolment.enrolmentschool.src.dto.response;

import enrolment.enrolmentschool.src.domain.Enrolment;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetEnrolmentResponse {
    @ApiModelProperty(example = "--님의 수강신청 현황")
    private String message;

    private Long enrolmentId;
    private String memberId;
    private Long subjectId;
    private String subjectName;

    public static GetEnrolmentResponse of(Enrolment enrolment){
        return GetEnrolmentResponse.builder()
                .message(enrolment.getMember().getName()+"님의 수강신청 현황")
                .enrolmentId(enrolment.getEnrolmentId())
                .subjectId(enrolment.getSubject().getId())
                .subjectName(enrolment.getSubject().getName())
                .memberId(enrolment.getMember().getId())
                .build();
    }
}

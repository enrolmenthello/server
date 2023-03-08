package enrolment.enrolmentschool.src.dto.request;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
public class PostEnrolmentRequest {
    @ApiParam(value="학번", example = "0")
    @ApiModelProperty(example = "12345678")
    private String memberId;

    @ApiParam(value="강좌번호", example = "0")
    @ApiModelProperty(example = "1234")
    private Long subjectId;

//
//    @ApiParam(value="과목이름", example = "0")
//    private String name;
//    @ApiParam(value="교수이름", example = "0")
//    private String professor;
//
//    @ApiParam(value="과목시간", example = "0")
//    private LocalTime time;
//
//    @ApiParam(value="학점", example = "0")
//    private int gradePoint;
//
//    @ApiParam(value="과목개수", example = "0")
//    private int stockQuantity;
//
//    @ApiParam(value="수강신청Id", example = "0")
//    private Long enrolmentId;













}

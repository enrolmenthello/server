package enrolment.enrolmentschool.src.dto.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class PostEnrolmentRequest {

    @ApiParam(value= "회원 ID", example = "0")
    private Long memberId;

    @ApiParam(value= "과목 ID", example = "0")
    private Long id;

//    @ApiParam(value= "과목 이름", example = "0")
//    private String name;
//
//    @ApiParam(value= "과목 교수", example = "0")
//    private String professor;
//
//    @ApiParam(value= "과목 시간", example = "0")
//    private int time;
//
//    @ApiParam(value= "과목 학점", example = "0")
//    private int gradePoint;
//
//    @ApiParam(value= "과목 수", example = "0")
//    private int stockQuantity;














}

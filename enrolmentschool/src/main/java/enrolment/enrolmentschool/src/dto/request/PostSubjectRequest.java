package enrolment.enrolmentschool.src.dto.request;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

@Getter @Setter
@NoArgsConstructor
public class PostSubjectRequest {
    @ApiParam(value="과목번호", example = "0")
    @ApiModelProperty(example = "12345678")
    private Long subjectId;
}

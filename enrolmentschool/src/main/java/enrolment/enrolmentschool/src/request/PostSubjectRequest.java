package enrolment.enrolmentschool.src.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostSubjectRequest {
    @NotEmpty(message="강좌 번호를 ")
    private String subjectId;//강좌번호
    private String subjectName;//과목 이름

    private String subjectProfessor;//교수명
    private int subjectTime;//강좌시간
    private int enrolmentGrade;//학점
    private int stockQuantity;//최대수
}

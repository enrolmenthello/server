package enrolment.enrolmentschool.src.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PostEnrolmentGradeResponse {
    private int totalGrade;
}

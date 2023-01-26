package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.EnrolmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnrolmentSearch {
    private String memberName; //회원 이름
    private EnrolmentStatus enrolmentStatus;//주문 상태
}

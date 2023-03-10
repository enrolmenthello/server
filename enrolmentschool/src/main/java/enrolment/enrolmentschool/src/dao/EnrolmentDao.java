package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrolmentDao extends JpaRepository<Enrolment,Long> {

    List<Enrolment> findByEnrolmentId(String enrolmentId);

    Optional<Enrolment> findByMemberId(String id);

    List<Enrolment> findByMember(Member member);

    Optional<Enrolment> findByMemberAndSubject(Member member, Subject subject);
}

package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrolmentDao extends JpaRepository<Enrolment,Long> {

    List<Enrolment> findByMemberId(Long id);

    List<Enrolment> findByMember(Member member);
    List<Enrolment> findBySubjectId(Long id);


    List<Enrolment> findBySubject(Subject subject);
}

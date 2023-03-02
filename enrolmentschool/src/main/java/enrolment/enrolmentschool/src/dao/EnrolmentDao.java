package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrolmentDao extends JpaRepository<Enrolment,Long> {

//    List<Enrolment> findByMemberId(String member);


//    Optional<Enrolment> findByMemberId(String memberId);
    Optional<List<Enrolment>> findByMemberId(String memberId);
    List<Enrolment> findByEnrolmentId(String enrolmentId);

    List<Enrolment> findByMember(Member member);
    List<Enrolment> findBySubjectId(Long id);


    List<Enrolment> findBySubject(Subject subject);
}

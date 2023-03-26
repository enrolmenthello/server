package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import org.hamcrest.Matcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrolmentDao extends JpaRepository<Enrolment,Long> {

    List<Enrolment> findByMember(Member member);

    Optional<Enrolment> findByMemberAndSubject(Member member, Subject subject);

    void save(Matcher<Enrolment> any);

    Optional<Enrolment> findByMemberId(String id);
}

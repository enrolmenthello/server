package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Preload;
import enrolment.enrolmentschool.src.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PreloadDao extends JpaRepository<Preload,Long> {

        List<Preload> findByMemberId(Long id);

        List<Preload> findByMember(Member member);
        List<Preload> findBySubjectId(Long id);


        List<Preload> findBySubject(Subject subject);

    Optional<Preload> findByMemberAndSubject(Member member, Subject subject);
}
package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDao extends JpaRepository<Member, Long>{}

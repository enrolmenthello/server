package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDao extends JpaRepository<Member, String>{

}

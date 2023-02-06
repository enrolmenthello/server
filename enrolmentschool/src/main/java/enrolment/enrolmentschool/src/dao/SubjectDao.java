package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDao extends JpaRepository<Subject,Long> {

}

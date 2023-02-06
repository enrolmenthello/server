package enrolment.enrolmentschool.src.dao;

import enrolment.enrolmentschool.src.domain.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrolmentDao extends JpaRepository<Enrolment,Long> {

}

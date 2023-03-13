package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Long> {

}

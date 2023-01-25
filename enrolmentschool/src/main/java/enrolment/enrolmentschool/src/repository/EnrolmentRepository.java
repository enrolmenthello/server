package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.Enrolment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class EnrolmentRepository {

    private final EntityManager em;

    public void save(Enrolment enrolment){
        em.persist(enrolment);
    }
    public Enrolment findOne(Long id){
        return em.find(Enrolment.class,id);
    }

    // public List<Order> findAll(OrderSearch orderSearch) { ... }

}

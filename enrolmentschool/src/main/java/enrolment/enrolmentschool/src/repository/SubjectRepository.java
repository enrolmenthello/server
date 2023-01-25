package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.Subject;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class SubjectRepository {

    private final EntityManager em;

    public void save(Subject subject){
        if(subject.getSubjectId()==null){
            em.persist(subject);
        }else{
            em.merge(subject);
        }
    }

    public Subject findOne(Long id){
        return em.find(Subject.class,id);
    }

    public List<Subject> findAll(){
        return em.createQuery("select i from Subject i", Subject.class).getResultList();
    }
}

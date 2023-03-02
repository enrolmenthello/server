package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByMemberId(String memberId);
//    private static final String PATHNAME="data/";
//
//
//    private PostSubjectRequest postSubjectRequest;
//    private final EntityManager em;
//
//
//
//    public void save(Subject subject){
//        if(subject.getId()==null){
//            em.persist(subject);
//        }else{
//            em.merge(subject);
//        }
//    }
//
//    public Subject findOne(Long id){
//        return em.find(Subject.class,id);
//    }
//
//
//    public List<Subject> findAll(){
//        return em.createQuery("select i from Subject i", Subject.class).getResultList();
//    }

//    public Vector<Subject> findAll(String fileName) {
//        Vector<Subject> subjects = new Vector<Subject>();
//        try {
//            File file = new File(PATHNAME+fileName);
//            this.postSubjectRequest =new PostSubjectRequest();
//            while(postSubjectRequest.read(sc)) {
//                Subject subject = new Subject();
//                subject.set(postSubjectRequest);
//                subjects.add(subject);
//            }
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return subjects;
//    }


}

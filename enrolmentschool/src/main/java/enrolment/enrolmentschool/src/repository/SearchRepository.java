package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.domain.SubjectSearch;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchRepository {
    @PersistenceContext
    EntityManager em;

    public List<Subject> findAllByCriteria(SubjectSearch subjectSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);
        Root<Subject> o = cq.from(Subject.class);
        Join<Subject, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();
        //수강신청 상태 검색
        if (subjectSearch.getEnrolmentStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    subjectSearch.getEnrolmentStatus());
            criteria.add(status);
        }
        //회원 이름 검색
        if (StringUtils.hasText(subjectSearch.getSubjectName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            subjectSearch.getSubjectName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Subject> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }
}

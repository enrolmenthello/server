package enrolment.enrolmentschool.src.repository;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

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

    //JPQL로 검색기능 동적쿼리 실행
//     public List<Enrolment> findAllByString(EnrolmentSearch enrolmentSearch) {
//         String jpql = "select o From enrolment o join o.member m";
//         boolean isFirstCondition = true;
//         //주문 상태 검색
//         if (enrolmentSearch.getEnrolmentStatus() != null) {
//             if (isFirstCondition) {
//                 jpql += " where";
//                 isFirstCondition = false;
//             } else {
//                 jpql += " and";
//             }
//             jpql += " o.status = :status";
//         }
//         //회원 이름 검색
//         if (StringUtils.hasText(enrolmentSearch.getMemberName())) {
//             if (isFirstCondition) {
//                 jpql += " where";
//                 isFirstCondition = false;
//             } else {
//                 jpql += " and";
//             }
//             jpql += " m.name like :name";
//         }
//         TypedQuery<Enrolment> query = em.createQuery(jpql, Enrolment.class)
//                 .setMaxResults(1000); //최대 1000건
//         if (enrolmentSearch.getEnrolmentStatus() != null) {
//             query = query.setParameter("status", enrolmentSearch.getEnrolmentStatus());
//         }
//         if (StringUtils.hasText(enrolmentSearch.getMemberName())) {
//             query = query.setParameter("name", enrolmentSearch.getMemberName());
//         }
//         return query.getResultList();
//
//          }

    //jpa criteria로 처리
    public List<Enrolment> findAllByCriteria(EnrolmentSearch enrolmentSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Enrolment> cq = cb.createQuery(Enrolment.class);
        Root<Enrolment> o = cq.from(Enrolment.class);
        Join<Enrolment, Member> m = o.join("member", JoinType.INNER); //회원과 조인
        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if (enrolmentSearch.getEnrolmentStatus() != null) {
            Predicate status = cb.equal(o.get("status"),
                    enrolmentSearch.getEnrolmentStatus());
            criteria.add(status);
        }
        //회원 이름 검색
        if (StringUtils.hasText(enrolmentSearch.getMemberName())) {
            Predicate name =
                    cb.like(m.<String>get("name"), "%" +
                            enrolmentSearch.getMemberName() + "%");
            criteria.add(name);
        }
        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Enrolment> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건
        return query.getResultList();
    }

}

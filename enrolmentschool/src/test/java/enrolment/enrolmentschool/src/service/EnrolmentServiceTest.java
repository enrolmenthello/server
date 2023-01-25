package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.EnrolmentStatus;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.exception.NotEnoughStockException;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnrolmentServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired EnrolmentService enrolmentService;
    @Autowired
    EnrolmentRepository enrolmentRepository;

    @Test
    public void 수강신청() throws Exception{

        //given
        Member member=createMember();
        Subject subject=createSubject("jpa 실전",3,10);
        int subjectCount=2;

        //when
        Long enrolmentId=enrolmentService.enrolment(member.getId(),subject.getSubjectId(),
                subjectCount);

        //then
        Enrolment getEnrolment=enrolmentRepository.findOne(enrolmentId);
        assertEquals("수강 신청시 상태는 ENROLMENT", EnrolmentStatus.REGISTER,getEnrolment.getStatus());
        assertEquals("수강신청한 과목 수가 정확해야 한다.",1,getEnrolment.getEnrolmentSubjects().size());
        assertEquals("수강신청한 학점은 학점*과목이다",3*2,getEnrolment.getTotalGrade());
        assertEquals("수강신청한 수만큼 학점이 줄어야 한다. ",8,subject.getStockQuantity());

    }

    @Test
    public void 수강신청취소(){
        Member member=createMember();
        Subject subject=createSubject("jpa 실전",3,10);
        int subjectCount=2;

        Long enrolmentId=enrolmentService.enrolment(member.getId(), subject.getSubjectId(),subjectCount);

        //when
        enrolmentService.cancelEnrolment(enrolmentId);

        //then
        Enrolment getEnrolment=enrolmentRepository.findOne(enrolmentId);

        assertEquals("수강 취소시 상태는 CANCEL 이다.",EnrolmentStatus.CANCLE,getEnrolment.getStatus());
        assertEquals("수강신청이 취소된 과목은 그만큼 과목수가 증가해야 한다.",10,subject.getStockQuantity());


    }

    private Member createMember(){
        Member member=new Member();
        member.setName("이승학");
        em.persist(member);
        return member;
    }

    private Subject createSubject(String subjectName,int enrolmentGrade, int stockQuantity){
        Subject subject=new Subject();
        subject.setSubjectName(subjectName);
        subject.setStockQuantity(stockQuantity);
        subject.setEnrolmentGrade(enrolmentGrade);
        em.persist(subject);
        return subject;

    }

    @Test(expected = NotEnoughStockException.class)
    public void 수강신청_학점초과() throws Exception{

        //given
        Member member=createMember();
        Subject subject=createSubject("jpa 실전",3,10);

        int subjectCount=11;

        //when
        enrolmentService.enrolment(member.getId(),subject.getSubjectId(),subjectCount);

        //then
        fail("과목 수량 부족 예외가 발생해야 한다.");
    }


}

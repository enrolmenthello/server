package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.EnrolmentSubject;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EnrolmentService {
    private final MemberRepository memberRepository;
    private final EnrolmentRepository enrolmentRepository;
    private final SubjectRepository subjectRepository;

    /**신청**/
    @Transactional
    public Long enrolment(Long memberId, Long subjectId, int count){

        //엔티티 조회
        Member member=memberRepository.findOne(memberId);
        Subject subject=subjectRepository.findOne(subjectId);

        //수강신청과목 생성
        EnrolmentSubject enrolmentSubject=EnrolmentSubject.createEnrolmentSubject(subject,subject.getEnrolmentGrade(),count);

        //수강신청 생성
        Enrolment enrolment=Enrolment.createEnrolment(member,enrolmentSubject);

        //수강신청 저장
        enrolmentRepository.save(enrolment);
        return enrolment.getEnrolmentId();
    }

    /**수강신청**/
    @Transactional
    public void cancelEnrolment(Long enrolmentId){

        //수강신청 엔티티 조회
        Enrolment enrolment=enrolmentRepository.findOne(enrolmentId);
        //수강신청 취소
        enrolment.cancel();
    }

    /**수강신청 검색*/
//    public List<Enrolment> findEnrolments(EnrolmentSearch enrolmentSearch){
//        return enrolmentRepository.findAll(enrolmentSearch);
//    }

}

package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;

//import enrolment.enrolmentschool.src.domain.EnrolmentSubject;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.response.GetTotalGrade;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import enrolment.enrolmentschool.src.dto.response.CancelEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.GetEnrolmentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EnrolmentService {
    private final MemberRepository memberRepository;
    private final EnrolmentRepository enrolmentRepository;
    private final SubjectRepository subjectRepository;
    private final EnrolmentDao enrolmentDao;

    private final MemberDao memberDao;

    private final SubjectDao subjectDao;

    /**
     * 수강신청
     **/
//        public List<PostEnrolmentResponse> enrolment(Long memberId, String subjectId, int count){
//
//            List<PostEnrolmentResponse> postEnrolmentResponseList=new ArrayList<>();
//        //엔티티 조회
//        Member member=memberRepository.findOne(memberId);
//        Subject subject=subjectRepository.findOne(subjectId);
//
//        //수강신청과목 생성
//        EnrolmentSubject enrolmentSubject=EnrolmentSubject.createEnrolmentSubject(subject,subject.getEnrolmentGrade(),count);
//
//        //수강신청 생성
//        Enrolment enrolment=Enrolment.createEnrolment(member,enrolmentSubject);
//
//        PostEnrolmentResponse postEnrolmentResponse=PostEnrolmentResponse.builder()
//                .subjectId(subject.getSubjectId())
//                .subjectName(subject.getSubjectName())
//                .enrolmentGrade(subject.getEnrolmentGrade())
//                .subjectProfessor(subject.getSubjectProfessor())
//                .subjectTime(subject.getSubjectTime())
//                        .build();
//        postEnrolmentResponseList.add(postEnrolmentResponse);
//
//            //수강신청 저장
//
//        return postEnrolmentResponseList;
//    }
    public List<GetEnrolmentResponse> enrolment(Long subjectId) {
        List<GetEnrolmentResponse> getEnrolmentResponseList = new ArrayList<>();


        //엔티티 조회
        Optional<Subject> subject = subjectDao.findById(subjectId);
        if (subject.isEmpty()) {
            throw new NotFoundSubjectException();
        }
        List<Enrolment> enrolment = enrolmentDao.findBySubject(subject.get());
        if (enrolment.size() == 0) {
            GetEnrolmentResponse getEnrolmentResponse = GetEnrolmentResponse.builder()
                    .message("수강신청한 과목이 없다.")
                    .build();
            getEnrolmentResponseList.add(getEnrolmentResponse);
            return getEnrolmentResponseList;
        }
        if(enrolment.size()!=0){
            for(int i=0;i<enrolment.size();i++){
                GetEnrolmentResponse getEnrolmentResponse=GetEnrolmentResponse.of(enrolment.get(i));
                getEnrolmentResponseList.add(getEnrolmentResponse);
            }
        }
        return getEnrolmentResponseList;
        //수강신청과목 생성
//        EnrolmentSubject enrolmentSubject= EnrolmentSubject.createEnrolmentSubject(subject,subject.getGradePoint());

        //수강신청 생성
//        Enrolment enrolment=Enrolment.createEnrolment(member,enrolmentSubject);

        //수강신청 저장
//        enrolmentRepository.save(enrolment);
//        return GetEnrolmentResponse.builder()
//                .message("수강신청이 완료되었습니다")
//                .build();
    }



    /**수강신청**/

    public CancelEnrolmentResponse cancelEnrolment(Long enrolmentId){

        //수강신청 엔티티 조회
        Optional<Enrolment> enrolment=enrolmentDao.findById(enrolmentId);

        //수강신청 취소
        enrolmentDao.delete(enrolment.get());
        return CancelEnrolmentResponse.builder()
                .message("해당 과목을 취소 했습니다")
                .build();
    }

//    /**수강신청 학점 조회**/
//
//    public GetTotalGrade totalGrade(Long enrolmentId) {
//        Optional<Enrolment> enrolment=enrolmentDao.findById(enrolmentId);
//
//        GetTotalGrade getTotalGrade=enrolment.get().
//
//
//
//    }

    /**수강신청 검색*/
//    public List<Enrolment> findEnrolments(EnrolmentSearch enrolmentSearch){
//        return enrolmentRepository.findAll(enrolmentSearch);
//    }



}

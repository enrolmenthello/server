package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;

//import enrolment.enrolmentschool.src.domain.EnrolmentSubject;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.domain.SubjectSearch;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentCancelRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentRequest;
import enrolment.enrolmentschool.src.dto.response.GetTotalGrade;
import enrolment.enrolmentschool.src.dto.response.PostEnrolmentResponse;
import enrolment.enrolmentschool.src.exception.enrolment.FailedEnrolmentSaveException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.preload.FailedPreloadSaveException;
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

    /**
     * 수강신청 case 1*/
//    public List<GetEnrolmentResponse> enrolment(Long subjectId) {
//        List<GetEnrolmentResponse> getEnrolmentResponseList = new ArrayList<>();
//
//
//        //엔티티 조회
//        Optional<Subject> subject = subjectDao.findById(subjectId);
//        if (subject.isEmpty()) {
//            throw new NotFoundSubjectException();
//        }
//        List<Enrolment> enrolment = enrolmentDao.findBySubject(subject.get());
//        if (enrolment.size() == 0) {
//            GetEnrolmentResponse getEnrolmentResponse = GetEnrolmentResponse.builder()
//                    .message("수강신청한 과목이 없다.")
//                    .build();
//            getEnrolmentResponseList.add(getEnrolmentResponse);
//            return getEnrolmentResponseList;
//        }
//        if(enrolment.size()!=0){
//            for(int i=0;i<enrolment.size();i++){
//                GetEnrolmentResponse getEnrolmentResponse=GetEnrolmentResponse.of(enrolment.get(i));
//                getEnrolmentResponseList.add(getEnrolmentResponse);
//            }
//        }
//        return getEnrolmentResponseList;

    /**수강신청
     * case2
     */
    @Transactional
    public PostEnrolmentResponse enrolment(PostEnrolmentRequest postEnrolmentRequest) {
        Optional<Member> member = memberDao.findById(postEnrolmentRequest.getMemberId());
        if (member.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Optional<Subject> subject=subjectDao.findById(postEnrolmentRequest.getSubjectId());
        Subject subjects=Subject.builder()
                .id(subject.get().getId())
                .name(subject.get().getName())
                .stockQuantity(subject.get().getStockQuantity())
                .gradePoint(subject.get().getGradePoint())
                .professor(subject.get().getProfessor())
                .time(subject.get().getTime())
                .build();

        try{
            Enrolment enrolment=Enrolment.builder()
                    .subject(subjects)
                    .member(member.get())
                    .professor(subjects.getProfessor())
                    .name(subjects.getName())
                    .time(subjects.getTime())
                    .stockQuantity(subjects.getStockQuantity())
                    .gradePoint(subjects.getGradePoint())
                    .build();
            enrolmentDao.save(enrolment);

            return PostEnrolmentResponse.builder()
                    .message("수강신청이 완료되었습니다.")
                    .professor(subjects.getProfessor())
                    .gradePoint(subjects.getGradePoint())
                    .name(subjects.getName())
                    .time(subjects.getTime())
                    .stockQuantity(subjects.getStockQuantity())
                    .enrolmentId(enrolment.getEnrolmentId())
                    .build();
        }catch (Exception e){
            throw new FailedEnrolmentSaveException();

        }

    }

//    private Subject saveSubject(Subject subjectId){
//        Subject subject=Subject.builder()
//                .id(subjectId.getId())
//                .name(subjectId.getName())
//                .professor(subjectId.getProfessor())
//                .gradePoint(subjectId.getGradePoint())
//                .stockQuantity(subjectId.getStockQuantity())
//                .time(subjectId.getTime())
//                .build();
//        subjectDao.save(subject);
//        return subject;
//    }


    //수강신청과목 생성
//        EnrolmentSubject enrolmentSubject= EnrolmentSubject.createEnrolmentSubject(subject,subject.getGradePoint());

        //수강신청 생성
//        Enrolment enrolment=Enrolment.createEnrolment(member,enrolmentSubject);

        //수강신청 저장
//        enrolmentRepository.save(enrolment);
//        return GetEnrolmentResponse.builder()
//                .message("수강신청이 완료되었습니다")
//                .build();




    /**수강신청**/

    @Transactional
    public CancelEnrolmentResponse cancelEnrolment(PostEnrolmentCancelRequest postEnrolmentCancelRequest){

        //수강신청 엔티티 조회
        Optional<Enrolment> enrolment=enrolmentDao.findById(postEnrolmentCancelRequest.getEnrolmentId());

        //수강신청 취소
        enrolmentDao.delete(enrolment.get());
        return CancelEnrolmentResponse.builder()
                .message("해당 과목을 취소 했습니다")
                .build();
    }

//    @Transactional
//    public List<PostEnrolmentResponse> enrolment(PostEnrolmentRequest postEnrolmentRequest) {
//        Optional<Member> member=memberDao.findById(postEnrolmentRequest.getMemberId());
//        if (member.isEmpty()) {
//            throw new NotFoundMemberException();
//        }
//        List<Subject> subject=subjectDao.findByMember(member.get());
//
//        List<PostEnrolmentResponse> arr=new ArrayList<>();
//        for(int i=0;i<subject.size();i++) {
//            PostEnrolmentResponse postEnrolmentResponse = PostEnrolmentResponse.of(subject.get(i));
//                arr.add(postEnrolmentResponse);
//        }
//
//        return arr;
//    }

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

//    /**수강신청 검색*/
//    public List<Enrolment> findEnrolments(SubjectSearch subjectSearch){
//        return enrolmentRepository.findAllByCriteria(subjectSearch);
//    }



}

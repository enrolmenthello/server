package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;

//import enrolment.enrolmentschool.src.domain.EnrolmentSubject;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.domain.SubjectSearch;
import enrolment.enrolmentschool.src.dto.request.GetEnrolmentListRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentCancelRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentGradeRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentRequest;
import enrolment.enrolmentschool.src.dto.response.*;
import enrolment.enrolmentschool.src.exception.enrolment.FailedEnrolmentSaveException;
import enrolment.enrolmentschool.src.exception.enrolment.MaxGradeEnrolmentException;
import enrolment.enrolmentschool.src.exception.member.AlreadyExistMemberException;
import enrolment.enrolmentschool.src.exception.member.MaximumTotalGradeException;
import enrolment.enrolmentschool.src.exception.member.MemberException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.preload.FailedPreloadSaveException;
import enrolment.enrolmentschool.src.exception.subject.LimitSubjectStockQuantityException;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.model.MappingInstantiationException;
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
     * case2
     */
    @Transactional
    public PostEnrolmentResponse enrolment(PostEnrolmentRequest postEnrolmentRequest) {
        Member member = memberDao.findById(postEnrolmentRequest.getMemberId()).orElseThrow(() -> new NotFoundMemberException());


        Subject subject = subjectDao.findById(postEnrolmentRequest.getSubjectId()).orElseThrow(() -> new NotFoundSubjectException());
        if(subject==null){
            throw new NotFoundSubjectException();
        }
        Subject subjects = Subject.builder()
                .id(subject.getId())
                .name(subject.getName())
                .stockQuantity(subject.getStockQuantity())
                .gradePoint(subject.getGradePoint())
                .professor(subject.getProfessor())
                .time(subject.getTime())
                .build();

//        List<Enrolment> enrolmentList = enrolmentDao.findByMemberId(postEnrolmentRequest.getMemberId())
//                .get();

//        for (Enrolment enrolment : enrolmentList) {
//            System.out.println("enrolment.getEnrolmentId() = " + enrolment.getEnrolmentId());
//        }
//
//        for(int i=0;i<enrolmentList.size();i++) {
//            Enrolment enrolment = enrolmentList.get(i);
//
//            Enrolment enrolment1 = Enrolment.builder()
//                    .gradePoint(enrolment.getGradePoint())
//                    .build();
//        }
//
//        int totalGrades = 0;
//        for (Enrolment e : enrolmentList) {
//            totalGrades += e.getGradePoint();
//        }
//        totalGrades += enrolment1.getGradePoint();
//
//        if (totalGrades > 18) {
//            throw new MaxGradeEnrolmentException();
//        }

        member.updateTotalGrade(subject.getGradePoint());
        if (member.getTotalGrade() > 18) {
            throw new MaximumTotalGradeException();
        }
        subject.removeSubject();
        if(subject.getStockQuantity()<0){
            throw new LimitSubjectStockQuantityException();
        }
        try {

            enrolmentDao.save(
                    Enrolment.builder()
                            .subject(subjects)
                            .member(member)
                            .professor(subjects.getProfessor())
                            .name(subjects.getName())
                            .time(subjects.getTime())
                            .stockQuantity(subjects.getStockQuantity())
                            .gradePoint(subjects.getGradePoint())
//                        .totalGrade(enrolment.getTotalGrade())
                            .build()
            );

                return PostEnrolmentResponse.builder()
                        .message("수강신청이 완료되었습니다.")
                        .professor(subjects.getProfessor())
                        .gradePoint(subjects.getGradePoint())
                        .name(subjects.getName())
                        .time(subjects.getTime())
                        .stockQuantity(subjects.getStockQuantity())
//                        .enrolmentId(enrolmentList.get(i).getEnrolmentId())
//                    .totalGrade(enrolment.getTotalGrade())
                        .build();

        } catch (Exception e) {
            throw new FailedEnrolmentSaveException();
        }
    }




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
    @Transactional
    public List<GetEnrolmentListResponse> enrolmentSearchAll(GetEnrolmentListRequest getEnrolmentListRequest) {
        List<GetEnrolmentListResponse> getEnrolmentListResponses=new ArrayList<>();

        Optional<Member> member=memberDao.findById(getEnrolmentListRequest.getMemberId());
        if(member.isEmpty()){
            throw new NotFoundMemberException();
        }
        List<Enrolment> enrolmentList=enrolmentDao.findByMember(member.get());
        if(enrolmentList.size()!=0){
            for(int i=0;i<enrolmentList.size();i++){
                GetEnrolmentListResponse getEnrolmentListResponse=GetEnrolmentListResponse.of(enrolmentList.get(i));
                getEnrolmentListResponses.add(getEnrolmentListResponse);
            }
        }
        return getEnrolmentListResponses;


    }

//    public PostEnrolmentGradeResponse checkTotalCredits(PostEnrolmentGradeRequest postEnrolmentGradeRequest) throws Exception {
//        List<Enrolment> enrolments = enrolmentDao.findByMemberId(postEnrolmentGradeRequest.getMemberId());
//
//        Optional<Enrolment> enrolment=enrolmentDao.findById(postEnrolmentGradeRequest.getMemberId());
//        Enrolment enrolment1=Enrolment.builder()
//                .gradePoint(enrolment.get().getGradePoint())
//                .totalGrade(enrolment.get().getTotalGrade())
//                .build();
//
//        int totalGrades = 0;
//        for (Enrolment e : enrolments) {
//            totalGrades += e.getGradePoint();
//        }
//        totalGrades += enrolment1.getGradePoint();
//        if (totalGrades > 18) {
//            throw new Exception("수강신청 학점은 18학점을 초과할 수 없습니다.");
//        }
//        if (totalGrades > 18) {
//            throw new Exception("수강신청 학점은 18학점을 초과할 수 없습니다.");
//        }else{
//            return PostEnrolmentGradeResponse.builder()
//                    .totalGrade(totalGrades)
//                    .build();
//        }
//    }
//    public PostEnrolmentGradeResponse saveEnrolment(PostEnrolmentGradeRequest postEnrolmentGradeRequest) {
//        Optional<Enrolment> enrolment=enrolmentDao.findById(postEnrolmentGradeRequest.getMemberId());
//        enrolmentDao.save(enrolment.get());
//        return PostEnrolmentGradeResponse.builder()
//                .totalGrade(enrolment.get().getTotalGrade())
//                .build();
//    }


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


}

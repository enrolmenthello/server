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
import enrolment.enrolmentschool.src.exception.subject.AlreadyExistSubjectException;
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
        if(subject==null) {
            throw new NotFoundSubjectException();
        }
        if (enrolmentDao.findByMemberAndSubject(member, subject).isPresent()) {
            throw new AlreadyExistSubjectException();
        }

        Subject subjects = Subject.builder()
                .id(subject.getId())
                .name(subject.getName())
                .stockQuantity(subject.getStockQuantity())
                .gradePoint(subject.getGradePoint())
                .professor(subject.getProfessor())
                .time(subject.getTime())
                .build();

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
                            .build()
            );
                return PostEnrolmentResponse.builder()
                        .message("수강신청이 완료되었습니다.")
                        .professor(subjects.getProfessor())
                        .gradePoint(subjects.getGradePoint())
                        .name(subjects.getName())
                        .time(subjects.getTime())
                        .stockQuantity(subjects.getStockQuantity())

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

}

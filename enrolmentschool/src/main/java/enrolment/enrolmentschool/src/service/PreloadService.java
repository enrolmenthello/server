package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.PreloadDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Preload;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostPreloadRequest;
import enrolment.enrolmentschool.src.dto.response.*;
import enrolment.enrolmentschool.src.exception.enrolment.FailedEnrolmentSaveException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.preload.FailedPreloadSaveException;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PreloadService {
    private final EnrolmentDao enrolmentDao;

    private final PreloadDao preloadDao;

    private final MemberDao memberDao;

    private final SubjectDao subjectDao;

    @Transactional
    public PostPreloadResponse preload(PostPreloadRequest postPreloadRequest) {
        Optional<Member> member = memberDao.findById(postPreloadRequest.getMemberId());
        if (member.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Optional<Subject> subject=subjectDao.findById(postPreloadRequest.getSubjectId());
        Subject subjects=Subject.builder()
                .id(subject.get().getId())
                .name(subject.get().getName())
                .stockQuantity(subject.get().getStockQuantity())
                .gradePoint(subject.get().getGradePoint())
                .professor(subject.get().getProfessor())
                .time(subject.get().getTime())
                .build();

        try{
            Preload preload=Preload.builder()
                    .subject(subjects)
                    .member(member.get())
                    .professor(subjects.getProfessor())
                    .name(subjects.getName())
                    .time(subjects.getTime())
                    .stockQuantity(subjects.getStockQuantity())
                    .gradePoint(subjects.getGradePoint())
                    .build();
            preloadDao.save(preload);

            return PostPreloadResponse.builder()
                    .message("미리담기가 완료되었습니다.")
                    .professor(subjects.getProfessor())
                    .gradePoint(subjects.getGradePoint())
                    .name(subjects.getName())
                    .time(subjects.getTime())
                    .stockQuantity(subjects.getStockQuantity())
                    .preloadId(preload.getPreloadId())
                    .build();
        }catch (Exception e){
            throw new FailedPreloadSaveException();

        }

    }

    @Transactional
    public CancelPreloadResponse cancelPreload(Long preloadId) {
        //수강신청 엔티티 조회
        Optional<Preload> preload=preloadDao.findById(preloadId);

        //수강신청 취소
        preloadDao.delete(preload.get());
        return CancelPreloadResponse.builder()
                .message("해당 과목을 취소 했습니다")
                .build();
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
}

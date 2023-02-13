package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.PreloadDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Preload;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.response.*;
import enrolment.enrolmentschool.src.exception.enrolment.FailedEnrolmentSaveException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Api(tags="1. preload API")
@Slf4j
@Controller
@RequiredArgsConstructor
public class PreloadService {
    private final EnrolmentDao enrolmentDao;

    private final PreloadDao preloadDao;

    private final MemberDao memberDao;

    private final SubjectDao subjectDao;

    @Transactional
    public PostPreloadResponse preload(Member memberId, Subject subjectId) {
        Optional<Member> member = memberDao.findById(memberId.getId());
        if (member.isEmpty()) {
            throw new NotFoundMemberException();
        }

        Subject subject=saveSubject(subjectId);

        try{
            Preload preload=Preload.builder()
                    .subject(subject)
                    .member(member.get())
                    .professor(subject.getProfessor())
                    .name(subject.getName())
                    .time(subject.getTime())
                    .stockQuantity(subject.getStockQuantity())
                    .gradePoint(subject.getGradePoint())
                    .build();
            preloadDao.save(preload);

            return PostPreloadResponse.builder()
                    .message("미리담기가 완료되었습니다.")
                    .professor(subject.getProfessor())
                    .gradePoint(subject.getGradePoint())
                    .name(subject.getName())
                    .time(subject.getTime())
                    .stockQuantity(subject.getStockQuantity())
                    .preloadId(preload.getPreloadId())
                    .build();
        }catch (Exception e){
            throw new FailedEnrolmentSaveException();

        }

    }

    public CancelPreloadResponse cancelPreload(Long preloadId) {
        //수강신청 엔티티 조회
        Optional<Preload> preload=preloadDao.findById(preloadId);

        //수강신청 취소
        preloadDao.delete(preload.get());
        return CancelPreloadResponse.builder()
                .message("해당 과목을 취소 했습니다")
                .build();
    }

    private Subject saveSubject(Subject subjectId){
        Subject subject=Subject.builder()
                .id(subjectId.getId())
                .name(subjectId.getName())
                .professor(subjectId.getProfessor())
                .gradePoint(subjectId.getGradePoint())
                .stockQuantity(subjectId.getStockQuantity())
                .time(subjectId.getTime())
                .build();
        subjectDao.save(subject);
        return subject;
    }
}

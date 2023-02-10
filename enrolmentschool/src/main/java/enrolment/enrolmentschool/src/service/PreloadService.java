package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.PreloadDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Preload;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.response.CancelEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.CancelPreloadResponse;
import enrolment.enrolmentschool.src.dto.response.GetEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.GetPreloadResponse;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.EnrolmentRepository;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

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


    public List<GetPreloadResponse> preload(Long subjectId) {
        List<GetPreloadResponse> getPreloadResponses = new ArrayList<>();


        //엔티티 조회
        Optional<Subject> subject = subjectDao.findById(subjectId);
        if (subject.isEmpty()) {
            throw new NotFoundSubjectException();
        }
        List<Preload> preload = preloadDao.findBySubject(subject.get());
        if (preload.size() == 0) {
            GetPreloadResponse getPreloadResponse = GetPreloadResponse.builder()
                    .message("미리담기한 과목이 없다.")
                    .build();
            getPreloadResponses.add(getPreloadResponse);
            return getPreloadResponses;
        }
        if(preload.size()!=0){
            for(int i=0;i<preload.size();i++){
                GetPreloadResponse getPreloadResponse=GetPreloadResponse.of(preload.get(i));
                getPreloadResponses.add(getPreloadResponse);
            }
        }
        return getPreloadResponses;
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

    public CancelPreloadResponse cancelPreload(Long preloadId) {
        //수강신청 엔티티 조회
        Optional<Preload> preload=preloadDao.findById(preloadId);

        //수강신청 취소
        preloadDao.delete(preload.get());
        return CancelPreloadResponse.builder()
                .message("해당 과목을 취소 했습니다")
                .build();
    }
}

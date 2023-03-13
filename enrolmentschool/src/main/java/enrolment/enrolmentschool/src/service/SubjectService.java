package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.SearchRepository;
import enrolment.enrolmentschool.src.repository.SubjectsRepository;
import enrolment.enrolmentschool.src.dto.response.GetSubjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectsRepository subjectRepository;

    private final SearchRepository searchRepository;
    private final SubjectDao subjectDao;

    public List<Subject> getSubjectList() {
        List<Subject> subjectList = subjectDao.findAll();
        return subjectList;
    }

    public GetSubjectResponse getSubject(PostSubjectRequest postSubjectRequest) {
        Subject subject=subjectDao.findById(postSubjectRequest.getSubjectId()).orElse(null);
        if(subject==null){
            throw new NotFoundSubjectException();
        }
        return GetSubjectResponse.builder()
                .id(subject.getId())
                .time(subject.getTime())
                .name(subject.getName())
                .professor(subject.getProfessor())
                .gradePoint(subject.getGradePoint())
                .stockQuantity(subject.getStockQuantity())
                .build();
    }
}

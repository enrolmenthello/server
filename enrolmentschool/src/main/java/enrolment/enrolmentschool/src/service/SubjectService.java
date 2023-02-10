package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.domain.SubjectSearch;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.SearchRepository;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import enrolment.enrolmentschool.src.dto.response.GetSubjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SearchRepository searchRepository;
    private final SubjectDao subjectDao;

        /**과목 검색*/
    public List<Subject> findSubjects(SubjectSearch subjectSearch){
        return searchRepository.findAllByCriteria(subjectSearch);
    }
    @Transactional
    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

    @Transactional
    public Subject updateSubject(Long subjectId, String subjectName, int stockQuantity){//변경 감지 기능 -> 업데이트 칠 부분만 set해서 반환
        Subject findSubject=subjectRepository.findOne(subjectId);
//        findSubject.setSubjectId(param.getSubjectId());
        findSubject.setName(subjectName);
//        findSubject.setSubjectTime(param.getSubjectTime());
//        findSubject.setSubjectProfessor(param.getSubjectProfessor());
        findSubject.setStockQuantity(stockQuantity);
//        findSubject.setEnrolmentGrade(param.getEnrolmentGrade());
        return findSubject;
    }

    public List<Subject> findSubject(){
        return subjectRepository.findAll();
    }

    public Subject findOne(Long subjectId){
        return subjectRepository.findOne(subjectId);
    }

    public List<GetSubjectResponse> getSubjectList() {
        List<Subject> getSubjectList=subjectDao.findAll();
        List<GetSubjectResponse> getSubjectResponseList=new ArrayList<>();
        if(getSubjectList.size()!=0){
            for(int i=0;i<getSubjectResponseList.size();i++){
                GetSubjectResponse getSubjectResponse=GetSubjectResponse.builder()
                        .id(getSubjectList.get(i).getId())
                        .name(getSubjectList.get(i).getName())
                        .gradePoint(getSubjectList.get(i).getGradePoint())
                        .professor(getSubjectList.get(i).getProfessor())
                        .time(getSubjectList.get(i).getTime())
                        .build();
                getSubjectResponseList.add(getSubjectResponse);
            }
        }
        return getSubjectResponseList;
    }

    public Object getSubject(long id) {
        Subject subject=subjectDao.findById(id).orElse(null);
        if(subject==null){
            throw new NotFoundSubjectException();
        }
        return GetSubjectResponse.builder()
                .id(subject.getId())
                .time(subject.getTime())
                .name(subject.getName())
                .professor(subject.getProfessor())
                .gradePoint(subject.getGradePoint())
                .build();
    }
}

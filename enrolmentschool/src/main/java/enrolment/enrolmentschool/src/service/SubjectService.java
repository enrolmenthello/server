package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Transactional
    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

    @Transactional
    public Subject updateSubject(Long subjectId, String subjectName, int stockQuantity){//변경 감지 기능 -> 업데이트 칠 부분만 set해서 반환
        Subject findSubject=subjectRepository.findOne(subjectId);
//        findSubject.setSubjectId(param.getSubjectId());
        findSubject.setSubjectName(subjectName);
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
}

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

    public List<Subject> findSubject(){
        return subjectRepository.findAll();
    }

    public Subject findOne(Long subjectId){
        return subjectRepository.findOne(subjectId);
    }
}

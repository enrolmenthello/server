package enrolment.enrolmentschool.src.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import enrolment.enrolmentschool.src.dto.response.GetSubjectResponse;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
import enrolment.enrolmentschool.src.repository.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SubjectServiceTest {

    @Mock
    private SubjectDao subjectDao;

    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("과목 전체 리스트 조회하는 경우우")
    @Test
    public void testGetSubjectList() {
        // Given
        Subject subject1 = Subject.builder()
                .id(1L)
                .name("Subject 1")
                .professor("Professor 1")
                .time(LocalTime.parse("10:00"))
                .gradePoint(3)
                .stockQuantity(10)
                .build();

        Subject subject2 = Subject.builder()
                .id(2L)
                .name("Subject 2")
                .professor("Professor 2")
                .time(LocalTime.parse("11:00"))
                .gradePoint(3)
                .stockQuantity(20)
                .build();

        when(subjectDao.findAll()).thenReturn(Arrays.asList(subject1, subject2));

        // When
        List<Subject> subjectList = subjectService.getSubjectList();

        // Then
        assertEquals(2, subjectList.size());
        assertEquals(1L, subjectList.get(0).getId());
        assertEquals("Subject 1", subjectList.get(0).getName());
        assertEquals("Professor 1", subjectList.get(0).getProfessor());
        assertEquals(3, subjectList.get(0).getGradePoint());
        assertEquals(10, subjectList.get(0).getStockQuantity());

        assertEquals(2L, subjectList.get(1).getId());
        assertEquals("Subject 2", subjectList.get(1).getName());
        assertEquals("Professor 2", subjectList.get(1).getProfessor());
        assertEquals(3, subjectList.get(1).getGradePoint());
        assertEquals(20, subjectList.get(1).getStockQuantity());
    }

    @DisplayName("과목 검색하는 테스트")
    @Test
    public void 과목찾기(){
        //given
        PostSubjectRequest postSubjectRequest=new PostSubjectRequest();
        postSubjectRequest.setSubjectId(1l);
        Subject subject=Subject.builder()
                .id(1l)
                .name("절차")
                .professor("최성운")
                .time(LocalTime.parse("10:00"))
                .gradePoint(3)
                .stockQuantity(9)
                .build();

        //when
        when(subjectDao.findById(anyLong())).thenReturn(Optional.of(subject));
        GetSubjectResponse response=subjectService.getSubject(postSubjectRequest);

        //then
        assertNotNull(response);
        assertEquals(subject.getId(),response.getId());
        assertEquals(subject.getName(),response.getName());
        assertEquals(subject.getProfessor(),response.getProfessor());
        assertEquals(subject.getGradePoint(),response.getGradePoint());
        assertEquals(subject.getStockQuantity(),response.getStockQuantity());

    }

    @DisplayName("과목찾기 실패")
    @Test
    public void 과목찾기_실패(){
        //given
        PostSubjectRequest postSubjectRequest= PostSubjectRequest.builder()
                .subjectId(1654654l)
                .build();

        //when
        when(subjectDao.findById(anyLong())).thenReturn(Optional.empty());

        //then
        assertThrows(NotFoundSubjectException.class, ()->
                subjectService.getSubject(postSubjectRequest));

    }

}

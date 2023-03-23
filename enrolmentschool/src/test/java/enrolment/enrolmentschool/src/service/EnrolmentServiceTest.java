package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentRequest;
import enrolment.enrolmentschool.src.dto.response.PostEnrolmentResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


//@RunWith(MockitoJUnitRunner.class)
//public class EnrolmentServiceTest {
//
//    @Mock
//    private MemberDao memberDao;
//
//    @Mock
//    private SubjectDao subjectDao;
//
//    @Mock
//    private EnrolmentDao enrolmentDao;
//
//    @InjectMocks
//    private EnrolmentService enrolmentService;



//    @Test
//    public void 수강신청_성공() {
//        // given
//        String memberId = "1";
//        long subjectId = 2L;
//        Member member = Member.builder()
//                .id(memberId)
//                .name("John Doe")
//                .totalGrade(0)
//                .build();
//        Subject subject = Subject.builder()
//                .id(subjectId)
//                .name("Computer Science")
//                .stockQuantity(1)
//                .gradePoint(3)
//                .professor("Jane Smith")
//                .time(LocalTime.ofSecondOfDay(12))
//                .build();
//        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
//                .memberId(memberId)
//                .subjectId(subjectId)
//                .build();
//
//        when(memberDao.findById(memberId)).thenReturn(Optional.of(member));
//        when(subjectDao.findById(subjectId)).thenReturn(Optional.of(subject));
//        when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.empty());
//
//        // when
//        PostEnrolmentResponse response = enrolmentService.enrolment(request);
//
//        // then
//        assertThat(response.getMessage()).isEqualTo("수강신청이 완료되었습니다.");
//        assertThat(response.getProfessor()).isEqualTo("Jane Smith");
//        assertThat(response.getGradePoint()).isEqualTo(3);
//        assertThat(response.getName()).isEqualTo("Computer Science");
//        assertThat(response.getTime()).isEqualTo("Mon 10:00-12:00");
//
//        assertThat(member.getTotalGrade()).isEqualTo(3);
//
//        verify(memberDao).findById(memberId);
//        verify(subjectDao).findById(subjectId);
//        verify(enrolmentDao).findByMemberAndSubject(member, subject);
//        verify(enrolmentDao).save(any(Enrolment.class));
//    }

//    @Test
//    void 수강신청_성공2() {
//        // given
//        String memberId = "1";
//        Long subjectId = 1L;
//        Member member = Member.builder()
//                .id(memberId)
//                .name("John Doe")
//                .totalGrade(17)
//                .build();
//        Subject subject = Subject.builder()
//                .id(subjectId)
//                .name("Math")
//                .stockQuantity(5)
//                .gradePoint(3)
//                .professor("Dr. Lee")
//                .time(LocalTime.parse("10:00"))
//                .build();
//        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
//                .memberId(memberId)
//                .subjectId(subjectId)
//                .build();
//
//        when(memberDao.findById(memberId)).thenReturn(Optional.of(member));
//        when(subjectDao.findById(subjectId)).thenReturn(Optional.of(subject));
//        when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.empty());
//
//        // when
//        PostEnrolmentResponse response = enrolmentService.enrolment(request);
//
//        // then
//        verify(memberDao).findById(memberId);
//        verify(subjectDao).findById(subjectId);
//        verify(enrolmentDao).findByMemberAndSubject(member, subject);
//
//        assertEquals("수강신청이 완료되었습니다.", response.getMessage());
//        assertEquals("Dr. Lee", response.getProfessor());
//        assertEquals(3, response.getGradePoint());
//        assertEquals("Math", response.getName());
//        assertEquals("10:00", response.getTime());
//
//        verify(member).updateTotalGrade(3);
//        verify(subject).removeSubject();
//        verify(enrolmentDao).save(any(Enrolment.class));
//    }
//@Test
//void 수강신청_성공3() {
//    // Given
////    Member member = new Member(1L, "John", 0);
////    Subject subject = new Subject(1L, "Math", 10, 3, "Professor A", "Mon 9-11");
////    PostEnrolmentRequest request = new PostEnrolmentRequest(1L, 1L);
//    String memberId = "1";
//        Long subjectId = 1L;
//        Member member = Member.builder()
//                .id(memberId)
//                .name("John Doe")
//                .password("4321")
//                .totalGrade(17)
//                .build();
//        Subject subject = Subject.builder()
//                .id(subjectId)
//                .name("Math")
//                .stockQuantity(5)
//                .gradePoint(3)
//                .professor("Dr. Lee")
//                .time(LocalTime.parse("10:00"))
//                .build();
//        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
//                .memberId(memberId)
//                .subjectId(subjectId)
//                .build();
//
//    when(memberDao.findById(request.getMemberId())).thenReturn(Optional.of(member));
//    when(subjectDao.findById(request.getSubjectId())).thenReturn(Optional.of(subject));
//    when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.empty());
//
//    // When
//    PostEnrolmentResponse response = enrolmentService.enrolment(request);
//
//    // Then
//    assertEquals("수강신청이 완료되었습니다.", response.getMessage());
//    assertEquals("Professor A", response.getProfessor());
//    assertEquals(3, response.getGradePoint());
//    assertEquals("Math", response.getName());
//    assertEquals("Mon 9-11", response.getTime());
//}

//    @RunWith(SpringRunner.class)
//    @SpringBootTest
//    public class EnrolmentServiceTest {
//
//        @Autowired
//        private EnrolmentService enrolmentService;
//
//        @Autowired
//        private MemberDao memberDao;
//
//        @Autowired
//        private SubjectDao subjectDao;
//
//        @Autowired
//        private EnrolmentDao enrolmentDao;
//
//        @Test
//        public void testEnrolment() {
//            // Given
//            Member member =  Member.builder()
//                    .name("John")
//                    .password("password")
//                    .build();
//            memberDao.save(member);
//
//            Subject subject =Subject.builder()
//                    .name("test")
//                    .professor("최성운")
//                    .gradePoint(3)
//                    .stockQuantity(2)
//                    .build();
//            subjectDao.save(subject);
//
//            PostEnrolmentRequest request = new PostEnrolmentRequest();
//            request.setMemberId(member.getId());
//            request.setSubjectId(subject.getId());
//
//            // When
//            PostEnrolmentResponse response = enrolmentService.enrolment(request);
//
//            // Then
//            assertEquals("수강신청이 완료되었습니다.", response.getMessage());
//            assertEquals("최성운", response.getProfessor());
//            assertEquals(3, response.getGradePoint());
//            assertEquals("test", response.getName());
//            assertNotNull(response.getTime());
//
//            Optional<Enrolment> enrolment = enrolmentDao.findByMemberAndSubject(member, subject);
//            assertTrue(enrolment.isPresent());
//        }

@RunWith(MockitoJUnitRunner.class)
public class EnrolmentServiceTest {

    @Mock
    private MemberDao memberDao;

    @Mock
    private SubjectDao subjectDao;

    @Mock
    private EnrolmentDao enrolmentDao;

    @InjectMocks
    private EnrolmentService enrolmentService;

    @DisplayName("수강신청 선공")
    @Test
    public void testEnrolment() {
        // Arrange
        PostEnrolmentRequest postEnrolmentRequest = new PostEnrolmentRequest();
        postEnrolmentRequest.setMemberId("60171917");
        postEnrolmentRequest.setSubjectId(1L);

        Member member = Member.builder()
                .id("60171917")
                .password("4321")
                .name("이승학")
                .build();
        lenient().when(memberDao.findById("60171917")).thenReturn(Optional.of(member));

        Subject subject = Subject.builder()
                .id(1L)
                .name("Math")
                .stockQuantity(20)
                .gradePoint(3)
                .professor("최성운")
                .time(LocalTime.parse("10:00"))
                .build();
        when(subjectDao.findById(1L)).thenReturn(Optional.of(subject));

        Enrolment enrolment = Enrolment.builder()
                .subject(subject)
                .member(member)
                .professor(subject.getProfessor())
                .name(subject.getName())
                .time(subject.getTime())
                .gradePoint(subject.getGradePoint())
                .build();
        when(enrolmentDao.save((Enrolment) Mockito.any())).thenReturn(enrolment);

        // Act
        PostEnrolmentResponse response = enrolmentService.enrolment(postEnrolmentRequest);

        // Assert

        assertEquals("수강신청이 완료되었습니다.", response.getMessage());
        assertEquals("Jane", response.getProfessor());
        assertEquals(3, response.getGradePoint());
        assertEquals("Math", response.getName());
        assertEquals("MWF 10:00-11:30", response.getTime());
    }




    /**
     * 위에까지 수강신청 성공 테스트 case
     */

//    @Test(expected = NotFoundMemberException.class)
//    public void testEnrolment_WithNotFoundMemberException() {
//        // given
//        long memberId = 1L;
//        long subjectId = 2L;
//        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
//                .memberId(memberId)
//                .subjectId(subjectId)
//                .build();
//
//        when(memberDao.findById(memberId)).thenReturn(Optional.empty());
//
//        // when
//        enrolmentService.enrolment(request);
//
//        // then
//        // NotFoundMemberException expected
//    }
//
//    @Test(expected = NotFoundSubjectException.class)
//    public void testEnrolment_WithNotFoundSubjectException() {
//        // given
//        long memberId = 1L;
//        long subjectId = 2L;
//        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
//                .memberId(memberId)
//                .subjectId(subjectId)
//                .build();
//
//        when(memberDao.findById(memberId)).thenReturn(Optional.of(Member.builder().build()));
//        when(subjectDao.findById(subjectId)).thenReturn(Optional.empty());
//
//        // when
//        enrolmentService.enrolment(request);
//
//        // then
//        // NotFoundSubjectException expected
//    }

}




package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.EnrolmentDao;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.dao.SubjectDao;
import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentCancelRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentRequest;
import enrolment.enrolmentschool.src.dto.response.CancelEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.PostEnrolmentResponse;
import enrolment.enrolmentschool.src.exception.member.MaximumTotalGradeException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberIdException;
import enrolment.enrolmentschool.src.exception.subject.AlreadyExistSubjectException;
import enrolment.enrolmentschool.src.exception.subject.LimitSubjectStockQuantityException;
import enrolment.enrolmentschool.src.exception.subject.NotFoundSubjectException;
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
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.hamcrest.Matchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        assertEquals("최성운", response.getProfessor());
        assertEquals(3, response.getGradePoint());
        assertEquals("Math", response.getName());
        assertEquals(LocalTime.parse("10:00"), response.getTime());
    }

    @DisplayName("수강신청 취소 성공")
    @Test
    public void testCancelEnrolment() {
        // Arrange
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
        when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.of(enrolment));

        PostEnrolmentCancelRequest postEnrolmentCancelRequest = new PostEnrolmentCancelRequest();
        postEnrolmentCancelRequest.setMemberId("60171917");
        postEnrolmentCancelRequest.setSubjectId(1L);

        // Act
        CancelEnrolmentResponse response = enrolmentService.cancelEnrolment(postEnrolmentCancelRequest);

        // Assert
        assertEquals("수강취소가 완료되었습니다.", response.getMessage());
        assertEquals("최성운", response.getProfessor());
        assertEquals(3, response.getGradePoint());
        assertEquals("Math", response.getName());
        assertEquals(LocalTime.parse("10:00"), response.getTime());
    }



    /**
     * 수강신청 실패하는 경우
     */

    /**
     * 수강신청 회원이 없는 경우
     */
    @DisplayName("수강신청 실패 - 회원이 없을 경우")
    @Test
    public void testEnrolmentFailWithWrongMemberId() {
        // given
        PostEnrolmentRequest postEnrolmentRequest = new PostEnrolmentRequest();
        postEnrolmentRequest.setMemberId("12345678"); // 존재하지 않는 회원 ID

        //when
        when(memberDao.findById("12345678")).thenReturn(Optional.empty());

        // then
        assertThrows(NotFoundMemberException.class,
                () -> enrolmentService.enrolment(postEnrolmentRequest));

    }

    @DisplayName("수강신청 실패 - 과목이 없을 경우")
    @Test
    public void testEnrolmentFailWithWrongSubjectId() {
        // Arrange
        PostEnrolmentRequest postEnrolmentRequest = new PostEnrolmentRequest();
        postEnrolmentRequest.setMemberId("60171917");
        postEnrolmentRequest.setSubjectId(9999L); // 존재하지 않는 과목 ID

        Member member = Member.builder()
                .id("60171917")
                .password("4321")
                .name("이승학")
                .build();
        lenient().when(memberDao.findById("60171917")).thenReturn(Optional.of(member));

        when(subjectDao.findById(9999L)).thenReturn(Optional.empty());

        // Act
        assertThrows(NotFoundSubjectException.class,
                () -> enrolmentService.enrolment(postEnrolmentRequest));

    }

    @DisplayName("수강신청 실패 - 해당 과목이 이미 수강신청된 경우")
    @Test
    public void testEnrolmentWithAlreadyEnrolledSubject() {
        // Given
        String memberId = "60171917";
        Long subjectId = 2L;

        Member member = Member.builder()
                .id(memberId)
                .name("이승학")
                .totalGrade(15)
                .build();

        Subject subject = Subject.builder()
                .id(subjectId)
                .name("d")
                .stockQuantity(9)
                .gradePoint(3)
                .professor("최성운21")
                .time(LocalTime.parse("10:00"))
                .build();

        Enrolment enrolment = Enrolment.builder()
                .subject(subject)
                .member(member)
                .professor(subject.getProfessor())
                .name(subject.getName())
                .time(subject.getTime())
                .gradePoint(subject.getGradePoint())
                .build();

        when(memberDao.findById(memberId)).thenReturn(Optional.of(member));
        when(subjectDao.findById(subjectId)).thenReturn(Optional.of(subject));
        when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.of(enrolment));

        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
                .memberId(memberId)
                .subjectId(subjectId)
                .build();

        // When
        Throwable throwable = catchThrowable(() -> enrolmentService.enrolment(request));

        // Then
        assertThat(throwable).isInstanceOf(AlreadyExistSubjectException.class);
    }


    @DisplayName("수강신청 실패 - 해당 과목에 모든 수강생이 등록된 경우")
    @Test
    public void testEnrolmentFailWhenNoStockLeft() {
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
                .stockQuantity(0) // 모든 수강생이 등록된 경우
                .gradePoint(3)
                .professor("최성운")
                .time(LocalTime.parse("10:00"))
                .build();
        when(subjectDao.findById(1L)).thenReturn(Optional.of(subject));

        // Act
        assertThrows(LimitSubjectStockQuantityException.class,
                () -> enrolmentService.enrolment(postEnrolmentRequest));


    }

    @DisplayName("수강신청 실패-해당 학생의 수강학점이 18학점 이상일 경우")
    @Test
    public void testEnrolmentWithMaximumTotalGrade() {
        // Given
        String memberId = "1L";
        Long subjectId = 2L;

        Member member = Member.builder()
                .id(memberId)
                .name("John")
                .totalGrade(18) // 이미 18학점을 취득한 경우
                .build();

        Subject subject = Subject.builder()
                .id(subjectId)
                .name("Math")
                .stockQuantity(10)
                .gradePoint(3)
                .professor("Jane")
                .time(LocalTime.parse("10:00"))
                .build();

        when(memberDao.findById(memberId)).thenReturn(Optional.of(member));
        when(subjectDao.findById(subjectId)).thenReturn(Optional.of(subject));
        when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.empty());

        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
                .memberId(memberId)
                .subjectId(subjectId)
                .build();

        // When
        Throwable throwable = catchThrowable(() -> enrolmentService.enrolment(request));

        // Then
        assertThat(throwable).isInstanceOf(MaximumTotalGradeException.class);
    }

    @DisplayName("수강신청 실패- 수강신청한 과목이 더이상 없을 경우")
    @Test
    public void testEnrolmentWithLimitSubjectStockQuantity() {
        // Given
        String memberId = "1L";
        Long subjectId = 2L;

        Member member = Member.builder()
                .id(memberId)
                .name("John")
                .totalGrade(15)
                .build();

        Subject subject = Subject.builder()
                .id(subjectId)
                .name("Math")
                .stockQuantity(0) // 이미 모든 자리가 찬 경우
                .gradePoint(3)
                .professor("Jane")
                .time(LocalTime.parse("10:00"))
                .build();

        when(memberDao.findById(memberId)).thenReturn(Optional.of(member));
        when(subjectDao.findById(subjectId)).thenReturn(Optional.of(subject));
        when(enrolmentDao.findByMemberAndSubject(member, subject)).thenReturn(Optional.empty());

        PostEnrolmentRequest request = PostEnrolmentRequest.builder()
                .memberId(memberId)
                .subjectId(subjectId)
                .build();

        // When
        Throwable throwable = catchThrowable(() -> enrolmentService.enrolment(request));

        // Then
        assertThat(throwable).isInstanceOf(LimitSubjectStockQuantityException.class);
    }

}




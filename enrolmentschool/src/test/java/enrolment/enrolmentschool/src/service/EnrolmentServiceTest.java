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
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberIdException;
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
    @DisplayName("수강신청 실패 - 잘못된 회원 ID")
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

    @DisplayName("수강신청 실패 - 잘못된 과목 ID")
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
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> enrolmentService.enrolment(postEnrolmentRequest));

        // Assert
        assertEquals("Invalid subject ID", throwable.getMessage());
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
        Throwable throwable = assertThrows(IllegalStateException.class,
                () -> enrolmentService.enrolment(postEnrolmentRequest));

        // Assert
        assertEquals("No stock left for the subject", throwable.getMessage());
    }


}




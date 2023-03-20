package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.dto.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.dto.request.PostMemberLoginRequest;
import enrolment.enrolmentschool.src.dto.response.PostMemberLoginResponse;
import enrolment.enrolmentschool.src.dto.response.PostMemberResponse;


import enrolment.enrolmentschool.src.exception.member.AlreadyExistMemberException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberIdException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional

@ExtendWith(MockitoExtension.class)
 public class MemberServiceTest {

    @InjectMocks
    MemberServiceImpl memberService;

    @Mock
    MemberDao memberDao;

    @DisplayName("회원가입 성공")
    @Test
    void 회원가입() {
        //given
        PostMemberJoinRequest request=createMemberSignUpRequest();
        Member actualMembmer=request.toEntity();

        //stub
        lenient().when(memberDao.save(any())).thenReturn(actualMembmer);

        //when
        PostMemberResponse postMemberResponse=memberService.join(request);

        //then
        assertAll(
                () -> assertEquals(actualMembmer.getPassword(),postMemberResponse.getPassword()),
                () -> assertEquals(actualMembmer.getName(),postMemberResponse.getName()),
                () -> assertEquals(actualMembmer.getId(),postMemberResponse.getId())
        );
    }

    @DisplayName("회원가입 닉네임중복")
    @Test
    void 회원가입_닉네임중복(){
        //given
        PostMemberJoinRequest request=createMemberSignUpRequest();
        Member actualMember=request.toEntity();

        //when
        lenient().when(memberDao.findById(request.getId())).thenReturn(Optional.of(actualMember));

        //then
        assertThrows(AlreadyExistMemberException.class, () -> {
            memberService.join(request);
        });
    }

    @DisplayName("로그인 성공")
    @Test
    void 로그인_성공(){
        //given
        PostMemberLoginRequest request=PostMemberLoginRequest.builder().id("12").password("133234").build();
        Member actualMember=Member.builder().id(request.getId()).password(request.getPassword()).build();

        //when
        lenient().when(memberDao.findById(request.getId())).thenReturn(Optional.of(actualMember));
        PostMemberLoginResponse memberLoginResponse=memberService.login(request);

        //then
        assertAll(
                () -> assertEquals(actualMember.getId(),memberLoginResponse.getId()),
                () -> assertEquals(actualMember.getPassword(),memberLoginResponse.getPassword())
        );
    }

    @DisplayName("로그인_존재하지 않는 유저 접근")
    @Test
    void 로그인_실패_존재하지않는_유저(){
        //given
        PostMemberLoginRequest request=PostMemberLoginRequest.builder().id("12222").build();

        //when
        lenient().when(memberDao.findById(request.getId())).thenReturn(Optional.ofNullable(null));

        //then
        assertThrows(NotFoundMemberIdException.class,()->{
            memberService.login(request);
        });

    }

//    @DisplayName("로그인_유저의 존재하지 않는 비밀번호 접근")
//    @Test
//    void 로그인_실패_유저의_존재하지않는_비밀번호(){
//        //given
//        PostMemberLoginRequest request=PostMemberLoginRequest.builder().password("3234234").build();
//
//        //when
//        lenient().when(memberDao.findById(request.getPassword())).thenReturn(Optional.ofNullable(null));
//
//        //then
//        assertThrows(NotFoundMemberPasswordException.class,()->{
//            memberService.login(request);
//        });
//
//    }




    PostMemberJoinRequest createMemberSignUpRequest() {
        return PostMemberJoinRequest.builder()
                .id("11111111")
                .name("김선태")
                .password("0000")
                .build();
    }


}

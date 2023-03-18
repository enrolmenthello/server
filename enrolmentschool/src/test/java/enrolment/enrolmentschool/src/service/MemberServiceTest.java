package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.dto.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.dto.response.PostMemberResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

//    @DisplayName("회원 닉네임 중복")
//    @Test
//    void 회원가입 닉네임중복(){
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

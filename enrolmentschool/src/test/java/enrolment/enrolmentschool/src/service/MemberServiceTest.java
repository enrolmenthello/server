//package enrolment.enrolmentschool.src.service;
//
//import enrolment.enrolmentschool.src.request.PostMemberJoinRequest;
//import enrolment.enrolmentschool.src.repository.MemberRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.transaction.Transactional;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@Transactional
//public class MemberServiceTest {
//    @Autowired
//    MemberService memberService;
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    public void 회원가입() throws Exception {
//        //Given
//        PostMemberJoinRequest member = new PostMemberJoinRequest();
//        member.setName("kim2");
//        //When
//        Long saveId = memberService.join(member);
//        //Then
//        assertEquals(member, memberRepository.findOne(saveId));
//    }
//
//
////    @Test(expected = IllegalStateException.class)
////    public void 중복_회원_예외() throws Exception{
////        //given
////        Member member1=new Member();
////        member1.setName("kim2");
////
////        Member member2=new Member();
////        member2.setName("kim2");
////
////        //when
////        memberService.join(member1);
////        memberService.join(member2);
////
////
////        //Then
////        fail("예외가 발생해야 한다.");
////
////
////    }
//}
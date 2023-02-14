package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.dto.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.dto.request.PostMemberLoginRequest;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.member.NotJoinMemberException;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.dto.response.PostMemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor @Slf4j
public class MemberServiceImpl implements MemberService{
//    @Autowired
//    MemberRepository memberRepository;

    //위에 스프링 필드 주입 대신 생성자 주입 권장
    private final MemberRepository memberRepository;
    private final MemberDao memberDao;

    //@RequiredArgsConstructor을 사용하면 이것을 생략할 수 있다.
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    }

    /**
     * 회원가입
     **/


    public PostMemberResponse join(PostMemberJoinRequest postMemberJoinRequest) {
//        validateDuplicateMember(postMemberJoinRequest);//중복 검증
        String  memberId= postMemberJoinRequest.getId();
        Member joinId=memberDao.findById(memberId).get();
        if(joinId.getId().equals(postMemberJoinRequest.getId())){
            throw new NotJoinMemberException();
        }





        Member createMember=Member.builder()
                .id(memberId)
                .name(postMemberJoinRequest.getName())
                .password(postMemberJoinRequest.getPassword())
                .build();

        Member joinMember=memberDao.save(createMember);
        return PostMemberResponse.builder()
                .id(joinMember.getId())
                .name(joinMember.getName())
                .password(joinMember.getPassword())
                .build();
    }



    /**
     * 전체 회원 조회
     **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     *
     * 회원 로그인인     */

    public PostMemberResponse login(PostMemberLoginRequest postMemberLoginRequest) {
        String id = postMemberLoginRequest.getId();
        Member loginMember = memberDao.findById(id).get();
        if (loginMember.getPassword().equals(postMemberLoginRequest.getPassword())) {
            return PostMemberResponse.builder()
                    .id(id)
                    .name(loginMember.getName())
                    .build();
        }else{
            throw new NotFoundMemberException();
        }


    }


}

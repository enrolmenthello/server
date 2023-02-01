package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.request.PostMemberLoginRequest;
import enrolment.enrolmentschool.src.exception.NotFoundMemberException;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.response.PostMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
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

    @Override
    public PostMemberResponse join(PostMemberJoinRequest postMemberJoinRequest) {
//        validateDuplicateMember(postMemberJoinRequest);//중복 검증
        Long memberId= postMemberJoinRequest.getId();



        Member createMember=Member.builder()
                .id(memberId)
                .name(postMemberJoinRequest.getName())
                .password(postMemberJoinRequest.getPassword())
                .build();

        Member joinMember=memberDao.save(createMember);
        return PostMemberResponse.builder()
                .memberId(joinMember.getId())
                .memberName(joinMember.getName())
                .build();
    }

    private void validateDuplicateMember(PostMemberJoinRequest postMemberJoinRequest) {
        Optional<Member> findMmebers = memberDao.findById(postMemberJoinRequest.getId());
        if (!findMmebers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
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
        Long id = postMemberLoginRequest.getId();
        Member loginMember = memberRepository.findOne(id);
        if (loginMember.getPassword().equals(postMemberLoginRequest.getPassword())) {
            return PostMemberResponse.builder()
                    .memberId(id)
                    .memberName(loginMember.getName())
                    .build();
        }else{
            throw new NotFoundMemberException();
        }


    }




}

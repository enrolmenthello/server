package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.dto.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.dto.request.PostMemberLoginRequest;
import enrolment.enrolmentschool.src.dto.response.PostMemberLoginResponse;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberIdException;
import enrolment.enrolmentschool.src.exception.member.NotFoundMemberPasswordException;
import enrolment.enrolmentschool.src.exception.member.AlreadyExistMemberException;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import enrolment.enrolmentschool.src.dto.response.PostMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//@Transactional(readOnly = true)
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

@Transactional
    public PostMemberResponse join(PostMemberJoinRequest postMemberJoinRequest) {
//        validateDuplicateMember(postMemberJoinRequest);//중복 검증
        String  memberId= postMemberJoinRequest.getId();
//        Member checkMember=memberDao.findById(memberId).orElseThrow(()->new NotFoundMemberException());
if(memberDao.findById(memberId).isPresent()){
    throw new AlreadyExistMemberException();
}
//    Member checkMember=memberDao.findById(memberId).get();
//        if (memberId.equals(checkMember.getId())) {
//            throw new AlreadyExistMemberException();
//        }

//        if (memberId.equals(checkMember.getId())) {
//            throw new AlreadyExistMemberException();
//        }

    Member createMember = Member.builder()
            .id(memberId)
            .name(postMemberJoinRequest.getName())
            .password(postMemberJoinRequest.getPassword())
            .build();

    Member joinMember = memberDao.save(createMember);

    return PostMemberResponse.builder()
            .id(joinMember.getId())
            .name(joinMember.getName())
            .password(joinMember.getPassword())
            .message("회원가입에 완료했습니다.")
            .build();


    }

//    @Transactional
//    public boolean checkJoin(PostMemberCheckRequest postMemberCheckRequest) {
//        String  checkId= postMemberCheckRequest.getId();
//        Member checkMember=memberDao.findById(checkId).orElseThrow(()->new NotFoundMemberException());
//        if(checkId.equals(checkMember.getId())){
//            return false;
//        }else {
//            return true;
//        }
//    }



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

    @Transactional
    public PostMemberLoginResponse login(PostMemberLoginRequest postMemberLoginRequest) {
        String id = postMemberLoginRequest.getId();



        Member passwordMember = memberDao.findById(id).get();
        if (passwordMember.getPassword().equals(postMemberLoginRequest.getPassword())) {
            return PostMemberLoginResponse.builder()
                    .id(id)
                    .password(passwordMember.getPassword())
                    .message("로그인에 성공했습니다!")
                    .build();
        } else{
            throw new NotFoundMemberException();
        }


    }



}

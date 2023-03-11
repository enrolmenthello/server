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


    /**
     * 회원가입
     **/

@Transactional
    public PostMemberResponse join(PostMemberJoinRequest postMemberJoinRequest) {
        String  memberId= postMemberJoinRequest.getId();

    if (memberDao.findById(memberId).isPresent()) {
        throw new AlreadyExistMemberException();
    }

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

    /**
     *
     * 회원 로그인인     */

    @Transactional
    public PostMemberLoginResponse login(PostMemberLoginRequest postMemberLoginRequest) {
        String id = postMemberLoginRequest.getId();
        String password= postMemberLoginRequest.getPassword();

        Member member = memberDao.findById(id).orElseThrow(NotFoundMemberIdException::new);
        if (member.getPassword().equals(password)){
            return PostMemberLoginResponse.builder()
                    .id(id)
                    .password(password)
                    .message("로그인에 성공했습니다!")
                    .build();
        } else{
            throw new NotFoundMemberPasswordException();
        }
    }
}

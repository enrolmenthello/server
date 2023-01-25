package enrolment.enrolmentschool.src.service;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
//    @Autowired
//    MemberRepository memberRepository;

    //위에 스프링 필드 주입 대신 생성자 주입 권장
    private final MemberRepository memberRepository;

    //@RequiredArgsConstructor을 사용하면 이것을 생략할 수 있다.
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    }

    /**회원가입**/
    @Transactional//변경
    public Long join(Member member){
        validateDuplicateMember(member);//중복 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMmebers=memberRepository.findByName(member.getName());
        if(!findMmebers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**전체 회원 조회**/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}

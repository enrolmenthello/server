package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/member")
@Api(tags="2. member API")
@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @ApiOperation(value="회원 폼")
    @GetMapping(value="/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }

    @ApiOperation(value="회원 생성")
    @PostMapping(value="/members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member=new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @ApiOperation(value="회원 리스트")
    //추가
    @GetMapping(value="/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}

package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.service.EnrolmentService;
import enrolment.enrolmentschool.src.service.MemberService;
import enrolment.enrolmentschool.src.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/enrolment")
@Api(tags="1. enrolment API")
@Slf4j
@Controller
@RequiredArgsConstructor
public class EnrolmentController {
    private final EnrolmentService enrolmentService;
    private final MemberService memberService;
    private final SubjectService subjectService;

    @ApiOperation(value="수강신청 폼")
    @GetMapping(value="/enrolment")
    public String createForm(Model model){
        List<Member> members=memberService.findMembers();
        List<Subject> subjects=subjectService.findSubject();

        model.addAttribute("members",members);
        model.addAttribute("subjects",subjects);

        return "enrolment/enrolmentForm";
    }

    @ApiOperation(value="수강신청 실행")
    @PostMapping(value="/enrolment")
    public String enrolment(@RequestParam("memberId") Long memberId, @RequestParam("subjectId") Long subjectId,
                            @RequestParam("count") int count){
        enrolmentService.enrolment(memberId, subjectId, count);
        return "redirect:/enrolments";
    }

    @ApiOperation("수강신청 취소")
    @PostMapping(value="/enrolments/{enrolmentId}/cancel")
    public String cancelEnrolment(@PathVariable("enrolmentId") Long enrolmentId){
        enrolmentService.cancelEnrolment(enrolmentId);

        return "redirect:/enrolments";
    }

}

package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.service.EnrolmentService;
import enrolment.enrolmentschool.src.service.MemberService;
import enrolment.enrolmentschool.src.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EnrolmentController {
    private final EnrolmentService enrolmentService;
    private final MemberService memberService;
    private final SubjectService subjectService;

    @GetMapping(value="/enrolment")
    public String createForm(Model model){
        List<Member> members=memberService.findMembers();
        List<Subject> subjects=subjectService.findSubject();

        model.addAttribute("members",members);
        model.addAttribute("subjects",subjects);

        return "enrolment/enrolmentForm";
    }

    @PostMapping(value="/enrolment")
    public String order(@RequestParam("memberId") Long memberId,@RequestParam("subjectId") Long subjectId,
                        @RequestParam("count") int count){
        enrolmentService.enrolment(memberId, subjectId, count);
        return "redirect:/enrolments";
    }

    @PostMapping(value="/enrolments/{enrolmentId}/cancel")
    public String cancelEnrolment(@PathVariable("enrolmentId") Long enrolmentId){
        enrolmentService.cancelEnrolment(enrolmentId);

        return "redirect:/enrolments";
    }

}

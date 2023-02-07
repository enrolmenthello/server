package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.response.CancelEnrolmentResponse;
import enrolment.enrolmentschool.src.response.GetEnrolmentResponse;
import enrolment.enrolmentschool.src.response.PostEnrolmentResponse;
import enrolment.enrolmentschool.src.service.EnrolmentService;
import enrolment.enrolmentschool.src.service.MemberServiceImpl;
import enrolment.enrolmentschool.src.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="1. enrolment API")
@Slf4j
@Controller
@RequiredArgsConstructor
public class EnrolmentController {
    private final EnrolmentService enrolmentService;
    private final MemberServiceImpl memberServiceImpl;
    private final SubjectService subjectService;

//    @ApiOperation(value="수강신청 폼")
//    @GetMapping(value="/enrolment/form")
//    public String createForm(Model model){
//        List<Member> members= memberServiceImpl.findMembers();
//        List<Subject> subjects=subjectService.findSubject();
//
//        model.addAttribute("members",members);
//        model.addAttribute("subjects",subjects);
//
//        return "enrolment/enrolmentForm";
//    }

    @ApiOperation(value="수강신청 실행")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetEnrolmentResponse.class)
    })
    @PostMapping(value="/enrolment")
public ResponseEntity<?> enrolment(@RequestParam ("memberId") Long memberId, @RequestParam("subjectId") Long subjectId, @RequestParam("count") int count){
        return ResponseEntity.ok(new BaseResponse(enrolmentService.enrolment(memberId,subjectId,count)));


//    public String enrolment(@RequestParam("memberId") Long memberId, @RequestParam("subjectId") String subjectId,
//                            @RequestParam("count") int count){
//        enrolmentService.enrolment(memberId, subjectId, count);
//        return "redirect:/enrolments";
    }

    @ApiOperation("수강신청 취소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CancelEnrolmentResponse.class)
    })
        @PostMapping(value="/enrolment/cancel")
    public  ResponseEntity<?> cancelEnrolment(@RequestParam("enrolmentId") Long enrolmentId){
        return ResponseEntity.ok(enrolmentService.cancelEnrolment(enrolmentId));


    }


}

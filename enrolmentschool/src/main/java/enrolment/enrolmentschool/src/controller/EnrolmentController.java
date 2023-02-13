package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentRequest;
import enrolment.enrolmentschool.src.dto.response.CancelEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.GetEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.GetTotalGrade;
import enrolment.enrolmentschool.src.dto.response.PostEnrolmentResponse;
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
import org.springframework.web.bind.annotation.*;

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

    /**수강신청 case 1
     *
     *
     */
//    @ApiOperation(value="수강신청 실행")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK", response = GetEnrolmentResponse.class)
//    })
//    @PostMapping(value="/enrolment")
//public ResponseEntity<?> enrolment(@RequestParam ("subjectId") Long subjectId){
//        return ResponseEntity.ok(new BaseResponse(enrolmentService.enrolment(subjectId)));

    /**수강신청 case 2
     *
     *
     */

        @ApiOperation(value="수강신청 실행")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostEnrolmentResponse.class)
    })
    @PostMapping(value="/enrolment")
public ResponseEntity<?> enrolment(@RequestParam("memberId") Member member,@RequestParam("subjectId") Subject subject) {
            return ResponseEntity.ok(new BaseResponse(enrolmentService.enrolment(member,subject)));
        }
//    public String enrolment(@RequestParam("memberId") Long memberId, @RequestParam("subjectId") String subjectId,
//                            @RequestParam("count") int count){
//        enrolmentService.enrolment(memberId, subjectId, count);
//        return "redirect:/enrolments";
//}

    @ApiOperation("수강신청 취소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CancelEnrolmentResponse.class)
    })
        @PostMapping(value="/enrolment/cancel")
    public  ResponseEntity<?> cancelEnrolment(@RequestParam("enrolmentId") Long enrolmentId){
        return ResponseEntity.ok(enrolmentService.cancelEnrolment(enrolmentId));


    }

//    @ApiOperation("총 수강신청 학점 조회")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK", response = GetTotalGrade.class)
//    })
//    @PostMapping(value="/enrolment/total")
//    public  ResponseEntity<?> totalGrade(@RequestParam("enrolmentId") Long enrolmentId){
//        return ResponseEntity.ok(enrolmentService.totalGrade(enrolmentId));
//
//
//    }


}

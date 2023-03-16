package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.domain.Enrolment;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.GetEnrolmentListRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentCancelRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentGradeRequest;
import enrolment.enrolmentschool.src.dto.request.PostEnrolmentRequest;
import enrolment.enrolmentschool.src.dto.response.*;
import enrolment.enrolmentschool.src.service.EnrolmentService;
import enrolment.enrolmentschool.src.service.MemberServiceImpl;
import enrolment.enrolmentschool.src.service.SubjectService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags="1. enrolment API")
@RequestMapping("/enrolment")
@RestController
@RequiredArgsConstructor
public class EnrolmentController {
    private final EnrolmentService enrolmentService;
    private final MemberServiceImpl memberServiceImpl;
    private final SubjectService subjectService;

        @ApiOperation(value="수강신청 실행")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostEnrolmentResponse.class)
    })
    @PostMapping(value="")
public ResponseEntity<?> enrolment(@RequestBody PostEnrolmentRequest postEnrolmentRequest) {
            return ResponseEntity.ok(new BaseResponse(enrolmentService.enrolment(postEnrolmentRequest)));
        }

    @ApiOperation("수강신청 취소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CancelEnrolmentResponse.class)
    })
        @PostMapping(value="/cancel")
    public  ResponseEntity<?> cancelEnrolment(@RequestBody PostEnrolmentCancelRequest postEnrolmentCancelRequest){
        return ResponseEntity.ok(enrolmentService.cancelEnrolment(postEnrolmentCancelRequest));
    }

    @ApiOperation("수강신청 내역조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetEnrolmentListResponse.class)
    })
    @GetMapping("/searchAll")
    public ResponseEntity<?> enrolmentSearchAll(@RequestBody GetEnrolmentListRequest getEnrolmentListRequest){
        return ResponseEntity.ok(new BaseResponse(enrolmentService.enrolmentSearchAll(getEnrolmentListRequest)));
    }
}

package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.domain.SubjectSearch;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import enrolment.enrolmentschool.src.dto.response.GetSubjectResponse;
import enrolment.enrolmentschool.src.dto.response.PostSubjectResponse;
import enrolment.enrolmentschool.src.service.SubjectService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags="3. member API")
@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    /**
     * 과목 전체 목록
     */
    @ApiOperation(value="과목 전체 리스트")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetSubjectResponse.class)
    })
    @GetMapping(value="/alllist")
    private ResponseEntity<?> getSubjectList(){
        return ResponseEntity.ok(new BaseResponse(subjectService.getSubjectList()));
    }

    /**
     * - 과목 상세보기
     * */
    @ApiOperation(value = "과목 검색하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetSubjectResponse.class)
    })
    @GetMapping("/search")
    private ResponseEntity<?> getSubject(@RequestBody PostSubjectRequest postSubjectRequest)  {
        return ResponseEntity.ok(new BaseResponse(subjectService.getSubject(postSubjectRequest)));
    }
}

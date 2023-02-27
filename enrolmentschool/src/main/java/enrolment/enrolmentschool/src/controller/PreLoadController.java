package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.dto.request.GetEnrolmentListRequest;
import enrolment.enrolmentschool.src.dto.request.GetPreloadListRequest;
import enrolment.enrolmentschool.src.dto.request.PostPreloadCancelRequest;
import enrolment.enrolmentschool.src.dto.request.PostPreloadRequest;
import enrolment.enrolmentschool.src.dto.response.CancelPreloadResponse;
import enrolment.enrolmentschool.src.dto.response.GetEnrolmentListResponse;
import enrolment.enrolmentschool.src.dto.response.GetPreloadListResponse;
import enrolment.enrolmentschool.src.dto.response.PostPreloadResponse;
import enrolment.enrolmentschool.src.service.PreloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags="4. preload API")
@Slf4j
@Controller
@RequiredArgsConstructor
public class PreLoadController {
    private final PreloadService preloadService;

    @ApiOperation(value="미리담기 실행")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = PostPreloadResponse.class)
    })
    @PostMapping(value="/preload")
    public ResponseEntity<?> preload(@RequestBody PostPreloadRequest postPreloadRequest) {
        return ResponseEntity.ok(new BaseResponse(preloadService.preload(postPreloadRequest)));
    }

    @ApiOperation("미리담기 취소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CancelPreloadResponse.class)
    })
    @PostMapping(value="/preload/cancel")
    public  ResponseEntity<?> cancelPreload(@RequestBody PostPreloadCancelRequest postPreloadCancelRequest){
        return ResponseEntity.ok(preloadService.cancelPreload(postPreloadCancelRequest));


    }

    @ApiOperation("미리담기 내역조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetPreloadListResponse.class)
    })
    @GetMapping("preload/search/all")
    public ResponseEntity<?> prelaodSearchAll(@RequestBody GetPreloadListRequest getPreloadListRequest){
        return ResponseEntity.ok(new BaseResponse(preloadService.prelaodSearchAll(getPreloadListRequest)));
    }



}

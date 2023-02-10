package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.dto.response.CancelEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.CancelPreloadResponse;
import enrolment.enrolmentschool.src.dto.response.GetEnrolmentResponse;
import enrolment.enrolmentschool.src.dto.response.GetPreloadResponse;
import enrolment.enrolmentschool.src.service.PreloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags="1. enrolment API")
@Slf4j
@Controller
@RequiredArgsConstructor
public class PreLoadController {
    private final PreloadService preloadService;

    @ApiOperation(value="미리담기 실행")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetPreloadResponse.class)
    })
    @PostMapping(value="/preload")
    public ResponseEntity<?> preload(@RequestParam("subjectId") Long subjectId) {
        return ResponseEntity.ok(new BaseResponse(preloadService.preload(subjectId)));
    }

    @ApiOperation("미리담기 취소")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CancelPreloadResponse.class)
    })
    @PostMapping(value="/preload/cancel")
    public  ResponseEntity<?> cancelPreload(@RequestParam("enrolmentId") Long enrolmentId){
        return ResponseEntity.ok(preloadService.cancelPreload(enrolmentId));


    }



}

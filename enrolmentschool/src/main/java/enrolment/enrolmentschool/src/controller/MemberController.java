package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.config.BaseResponse;
import enrolment.enrolmentschool.src.dao.MemberDao;
import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.dto.request.PostMemberCheckRequest;
import enrolment.enrolmentschool.src.dto.request.PostMemberJoinRequest;
import enrolment.enrolmentschool.src.dto.request.PostMemberLoginRequest;

import enrolment.enrolmentschool.src.dto.response.PostMemberCheckResponse;
import enrolment.enrolmentschool.src.dto.response.PostMemberLoginResponse;
import enrolment.enrolmentschool.src.service.MemberServiceImpl;
import enrolment.enrolmentschool.src.dto.response.PostMemberResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="2. member API")
@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;
    private final MemberDao memberDao;

    @ApiOperation(value="회원 가입")
    @ApiResponses({
            @ApiResponse(code=200, message="OK", response = PostMemberResponse.class)
    })
    @PostMapping("/join")
private ResponseEntity<?> join(@RequestBody  PostMemberJoinRequest postMemberJoinRequest){
        return ResponseEntity.ok(new BaseResponse(memberServiceImpl.join(postMemberJoinRequest)));
    }

    //로그인
    @ApiOperation(value="회원 로그인")
    @ApiResponses({
            @ApiResponse(code=200, message="OK", response = PostMemberLoginResponse.class)
    })
    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody  PostMemberLoginRequest postMemberLoginRequest){
        return ResponseEntity.ok(new BaseResponse(memberServiceImpl.login(postMemberLoginRequest)));
    }

}

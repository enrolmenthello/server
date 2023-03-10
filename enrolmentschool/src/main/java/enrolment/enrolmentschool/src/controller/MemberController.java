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

//    @GetMapping(value="/new")
//    public String createForm(Model model){
//        model.addAttribute("memberForm",new MemberForm());
//        return "members/createMemberForm";
//    }

    @ApiOperation(value="회원 가입")
    @ApiResponses({
            @ApiResponse(code=200, message="OK", response = PostMemberResponse.class)
    })
    @PostMapping("/join")
private ResponseEntity<?> join(@RequestBody  PostMemberJoinRequest postMemberJoinRequest){
        return ResponseEntity.ok(new BaseResponse(memberServiceImpl.join(postMemberJoinRequest)));
    }

//    @ApiOperation(value="회원 아이디 중복확인")
//    @ApiResponses({
//            @ApiResponse(code=200, message="OK", response = boolean.class)
//    })
//    @PostMapping("/join/check")
//    private ResponseEntity<?> checkJoin(@RequestBody PostMemberCheckRequest postMemberCheckRequest){
//        return ResponseEntity.ok(new BaseResponse(memberServiceImpl.checkJoin(postMemberCheckRequest)));
//    }


    //로그인
    @ApiOperation(value="회원 로그인")
    @ApiResponses({
            @ApiResponse(code=200, message="OK", response = PostMemberLoginResponse.class)
    })
    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody  PostMemberLoginRequest postMemberLoginRequest){
        return ResponseEntity.ok(new BaseResponse(memberServiceImpl.login(postMemberLoginRequest)));
    }



    @ApiOperation(value="회원 리스트 조회")
    @ApiResponses({
            @ApiResponse(code=200, message = "OK",response = PostMemberResponse.class)
    })
    //추가
    @GetMapping(value="/list")
    public String list(Model model){
        List<Member> members= memberServiceImpl.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }


}

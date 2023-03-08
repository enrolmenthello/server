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
@Slf4j
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

//    @ApiOperation(value="과목 폼")
//    @GetMapping(value="/subjects/new")
//    public String createForm(Model model){
//        model.addAttribute("form",new SubjectForm());
//        return "subjects/createSubjectForm";
//    }
    /**
     * 과목 생성
     */
//    @ApiOperation(value = "과목 생성")
//    @PostMapping(value="/subjects/new")
//    public String create(SubjectForm form){
//        Subject subject=new Subject();
//        subject.setSubjectName(form.getSubjectName());
//        subject.setSubjectProfessor(form.getSubjectProfessor());
//        subject.setSubjectTime(form.getSubjectTime());
//        subject.setEnrolmentGrade(form.getEnrolmentGrade());
//        subject.setSubjectId(form.getSubjectId());
//        subject.setStockQuantity(form.getStockQuantity());
//
//        subjectService.saveSubject(subject);
//        return "redirect:/subjects";
//    }

    /**
     * 과목 전체 목록
     */
    @ApiOperation(value="과목 전체 리스트")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetSubjectResponse.class)
    })
    @GetMapping(value="/subjectList")
    private ResponseEntity<?> getSubjectList(){
        return ResponseEntity.ok(new BaseResponse(subjectService.getSubjectList()));
    }
//    public String list(Model model){
//        List<Subject> subjects=subjectService.findSubject();
//        model.addAttribute("subjects",subjects);
//        return "subjects/subjectList";
//    }

    /**
     * - 과목 상세보기
     * */
    @ApiOperation(value = "과목 검색하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetSubjectResponse.class)
    })
    @GetMapping("/subject/search")
    private ResponseEntity<?> getSubject(@RequestBody PostSubjectRequest postSubjectRequest)  {
        return ResponseEntity.ok(new BaseResponse(subjectService.getSubject(postSubjectRequest)));
    }

//    @ApiOperation(value = "과목 검색하기")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK", response = GetSubjectResponse.class)
//    })
//    @GetMapping("/subject/search")
//    private ResponseEntity<?> getSubject(@RequestParam("subjectSearch") @ApiParam(value = "과목 이름",example = "0", defaultValue = "융소") SubjectSearch subjectSearch)  {
//        return ResponseEntity.ok(new BaseResponse(subjectService.findSubjects(subjectSearch)));
//    }

    /**
     * 과목 수정 폼
     */
//    @ApiOperation(value="과목 업데이트")
//    @GetMapping(value="/subjects/{subjectId}/edit")
//    public String updateSubject(@PathVariable("subjectId")Long subjectId,Model model){
//        Subject subject=(Subject) subjectService.findOne(subjectId);
//
//        SubjectForm form=new SubjectForm();
//        form.setSubjectId(subject.getSubjectId());
//        form.setSubjectName(subject.getSubjectName());
//        form.setSubjectProfessor(subject.getSubjectProfessor());
//        form.setEnrolmentGrade(subject.getEnrolmentGrade());
//        form.setStockQuantity(subject.getStockQuantity());
//
//        model.addAttribute("form",form);
//        return "subjects/updateSubjectForm";
//
//    }

    /**
     * 과목 수정
     */
//    @ApiOperation(value = "과목 수정")
//    @PostMapping(value="/subjects/{subjectId}/edit")
//    public String updateSubject(@PathVariable Long subjectId, @ModelAttribute("form") SubjectForm form){
////        Subject subject=new Subject();
////        subject.setSubjectId(form.getSubjectId());
////        subject.setSubjectName(form.getSubjectName());
////        subject.setSubjectProfessor(form.getSubjectProfessor());
////        subject.setSubjectTime(form.getSubjectTime());
////        subject.setStockQuantity(form.getStockQuantity());
////        subject.setEnrolmentGrade(form.getEnrolmentGrade());
//
//        subjectService.updateSubject(subjectId, form.getSubjectName(), form.getStockQuantity());
//        return "redirect:/subjects";
//
//    }






}

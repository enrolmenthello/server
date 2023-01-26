package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/subject")
@Api(tags="3. member API")
@Controller
@Slf4j
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @ApiOperation(value="과목 폼")
    @GetMapping(value="/subjects/new")
    public String createForm(Model model){
        model.addAttribute("form",new SubjectForm());
        return "subjects/createSubjectForm";
    }

    @ApiOperation(value = "과목 생성")
    @PostMapping(value="/subjects/new")
    public String create(SubjectForm form){
        Subject subject=new Subject();
        subject.setSubjectName(form.getSubjectName());
        subject.setSubjectProfessor(form.getSubjectProfessor());
        subject.setSubjectTime(form.getSubjectTime());
        subject.setEnrolmentGrade(form.getEnrolmentGrade());
        subject.setSubjectId(form.getSubjectId());
        subject.setStockQuantity(form.getStockQuantity());

        subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    /**
     * 과목 목록
     */
    @ApiOperation(value="과목 리스트")
    @GetMapping(value="/subjects")
    public String list(Model model){
        List<Subject> subjects=subjectService.findSubject();
        model.addAttribute("subjects",subjects);
        return "subjects/subjectList";
    }

    /**
     * 과목 수정 폼
     */
    @ApiOperation(value="과목 업데이트")
    @GetMapping(value="/subjects/{subjectId}/edit")
    public String updateSubject(@PathVariable("subjectId")Long subjectId,Model model){
        Subject subject=(Subject) subjectService.findOne(subjectId);

        SubjectForm form=new SubjectForm();
        form.setSubjectId(subject.getSubjectId());
        form.setSubjectName(subject.getSubjectName());
        form.setSubjectProfessor(subject.getSubjectProfessor());
        form.setEnrolmentGrade(subject.getEnrolmentGrade());
        form.setStockQuantity(subject.getStockQuantity());

        model.addAttribute("form",form);
        return "subjects/updateSubjectForm";

    }

    /**
     * 과목 수정
     */
    @ApiOperation(value = "과목 수정")
    @PostMapping(value="/subjects/{subjectId}/edit")
    public String updateSubject(@PathVariable Long subjectId, @ModelAttribute("form") SubjectForm form){
//        Subject subject=new Subject();
//        subject.setSubjectId(form.getSubjectId());
//        subject.setSubjectName(form.getSubjectName());
//        subject.setSubjectProfessor(form.getSubjectProfessor());
//        subject.setSubjectTime(form.getSubjectTime());
//        subject.setStockQuantity(form.getStockQuantity());
//        subject.setEnrolmentGrade(form.getEnrolmentGrade());

        subjectService.updateSubject(subjectId, form.getSubjectName(), form.getStockQuantity());
        return "redirect:/subjects";

    }





}

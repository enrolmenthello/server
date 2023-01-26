package enrolment.enrolmentschool.src.controller;

import enrolment.enrolmentschool.src.domain.Subject;
import enrolment.enrolmentschool.src.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping(value="/subjects/new")
    public String createForm(Model model){
        model.addAttribute("form",new SubjectForm());
        return "subjects/createSubjectForm";
    }

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
    @GetMapping(value="/subjects")
    public String list(Model model){
        List<Subject> subjects=subjectService.findSubject();
        model.addAttribute("subjects",subjects);
        return "subjects/subjectList";
    }

    /**
     * 과목 수정 폼
     */
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

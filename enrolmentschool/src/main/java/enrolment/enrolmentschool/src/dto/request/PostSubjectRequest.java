package enrolment.enrolmentschool.src.dto.request;

import enrolment.enrolmentschool.src.domain.Member;
import enrolment.enrolmentschool.src.domain.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostSubjectRequest {
    private Long id;
    private String name;

    private String professor;
    private int time;
    private int gradePoint;
    private int stockQuantity;

//    public boolean read(String fileName) {
//        if(fileName.equals("data/")) {
//            this.id = fileName.;
//            this.name = sc.next();
//            this.professor = sc.next();
//            this.gradePoint = sc.next();
//            this.time = sc.next();
//            return true;
//        }
//        return false;
//    }

//    private void set(Subject subject) {
//        this.id = subject.getId();
//        this.name = subject.getName();
//        this.professor = subject.getProfessor();
//        this.gradePoint = subject.getGradePoint();
//        this.time = subject.getTime();
//    }
//
//    public void save(FileWriter fileWriter, Subject subject) {
//        this.set(subject);
//
//        try {
//            fileWriter.write(this.id + " " + this.name + " " + this.professor + " " + this.gradePoint + " " + this.time + "\n");
//        } catch (IOException var4) {
//            var4.printStackTrace();
//        }
//
//    }

}

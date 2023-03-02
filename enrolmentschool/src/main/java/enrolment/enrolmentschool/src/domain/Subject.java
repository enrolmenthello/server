package enrolment.enrolmentschool.src.domain;

import enrolment.enrolmentschool.src.exception.NotEnoughStockException;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Builder
@Getter @Setter
@AllArgsConstructor
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="subject_id")
    private Long id;
    private String name;

    private String professor;
    private LocalTime time;
    private int gradePoint;
    private int stockQuantity;//수강 가능 인원


    @Column(name="member_id")
    private String memberId;

//    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)hibernate_sequence
//    private List<EnrolmentSubject> enrolmentSubjects=new ArrayList<>();
//enrolments
//    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
//    private List<Preload> preloads=new ArrayList<>();

    public Subject() {

    }


//    public void set(PostSubjectRequest postSubjectRequest) {
//        this.id = postSubjectRequest.getId();
//        this.name = postSubjectRequest.getName();
//        this.professor = postSubjectRequest.getProfessor();
//        this.gradePoint = postSubjectRequest.getGradePoint();
//        this.time = postSubjectRequest.getTime();
//    }

    //비즈니스 로직//
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }

    public void removeSubject(int quantity){
        int restSubject=this.stockQuantity-quantity;
        if(restSubject<0){
            throw new NotEnoughStockException("need more subject");
        }
        this.stockQuantity=restSubject;
    }




}
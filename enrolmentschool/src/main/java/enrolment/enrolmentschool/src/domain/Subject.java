package enrolment.enrolmentschool.src.domain;

import enrolment.enrolmentschool.src.exception.NotEnoughStockException;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity

@DiscriminatorColumn(name="dtype")
@Getter @Setter
public class Subject {
    @Id @GeneratedValue
    @Column(name="subject_id")
    private Long id;
    private String name;

    private String professor;
    private int time;
    private int gradePoint;
    private int stockQuantity;

//    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)hibernate_sequence
//    private List<EnrolmentSubject> enrolmentSubjects=new ArrayList<>();
//enrolments
//    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
//    private List<Preload> preloads=new ArrayList<>();

    public Subject() {

    }


    public void set(PostSubjectRequest postSubjectRequest) {
        this.id = postSubjectRequest.getId();
        this.name = postSubjectRequest.getName();
        this.professor = postSubjectRequest.getProfessor();
        this.gradePoint = postSubjectRequest.getGradePoint();
        this.time = postSubjectRequest.getTime();
    }

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
package enrolment.enrolmentschool.src.domain;

import enrolment.enrolmentschool.src.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Table(name="subjects")
@Getter @Setter
public class Subject {
    @Id @GeneratedValue
    @Column(name="subject_id")
    private Long subjectId;
    private String subjectName;

    private String subjectProfessor;
    private int subjectTime;
    private int enrolmentGrade;
    private int stockQuantity;

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<EnrolmentSubject> enrolmentSubjects=new ArrayList<>();

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private List<Preload> preloads=new ArrayList<>();

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
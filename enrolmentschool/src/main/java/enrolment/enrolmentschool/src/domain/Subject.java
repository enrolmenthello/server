package enrolment.enrolmentschool.src.domain;

import enrolment.enrolmentschool.src.exception.NotEnoughStockException;
import enrolment.enrolmentschool.src.dto.request.PostSubjectRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="subject_id")
    private Long id;
    private String name;


    private String professor;
    private LocalTime time;
    private int gradePoint;
    private int stockQuantity;//수강신청 가능 인원

//
//    @Column(name="member_id")
//    private String memberId;

    //비즈니스 로직//
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }

    public void removeSubject(){
        int restSubject=this.stockQuantity-1;
        this.stockQuantity=restSubject;
    }

    public void addSubject() {
        this.stockQuantity++;
    }




}
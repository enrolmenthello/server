package enrolment.enrolmentschool.src.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@AllArgsConstructor
public  class Enrolment {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="enrolment_id")
    private Long enrolmentId;

    private String name;

    private String professor;
    private LocalTime time;
    private int gradePoint;
    private int stockQuantity;
    private int totalGrade;//취득한 학점




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    public Member member;

    @OneToOne
    @JoinColumn(name="subject_id")
    public Subject subject;




//    @OneToMany(mappedBy = "enrolment",cascade = CascadeType.ALL)
//    private List<EnrolmentSubject> enrolmentSubjects=new ArrayList<>();


//    @Enumerated(EnumType.STRING)
//    private EnrolmentStatus status;

    public Enrolment() {

    }


    public void setMember(Member member) {
        this.member = member;
        member.getEnrolments().add(this);
    }

//    public void addEnrolmentSubject(EnrolmentSubject enrolmentSubject){
//        enrolmentSubjects.add(enrolmentSubject);
//        enrolmentSubject.setEnrolment(this);
//    }

//    public static Enrolment createEnrolment(Member member, EnrolmentSubject... enrolmentSubjects){
//        Enrolment enrolment=new Enrolment();
//        enrolment.setMember(member);
//        for(EnrolmentSubject enrolmentSubject : enrolmentSubjects){
//            enrolment.addEnrolmentSubject(enrolmentSubject);
//        }
//        enrolment.setStatus(EnrolmentStatus.REGISTER);
//        return enrolment;
//
//    }

//    //수강신청 로직//
//    /**수강신청 취소**/
//    public void cancel(){
//        if(this.getStatus()==EnrolmentStatus.CANCLE){
//            throw new IllegalStateException("이미 수강신청한 상품은 취소가 불가능합니다.");
//        }
//        this.setStatus(EnrolmentStatus.CANCLE);
//        for(EnrolmentSubject enrolmentSubject : enrolmentSubjects){
//            enrolmentSubject.cancel();
//        }
//    }
//
//    ==조회 로직==//
//    /**전체 수강신청 학점 조회**/
//    public int getTotalGrade(){
//        int totalGrade=0;
//        for(EnrolmentSubject enrolmentSubject : enrolmentSubjects){
//            totalGrade+=enrolmentSubject.getTotalGrade();
//        }
//        return totalGrade;
//    }





}

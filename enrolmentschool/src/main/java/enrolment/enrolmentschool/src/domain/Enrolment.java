package enrolment.enrolmentschool.src.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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





    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    public Member member;

    @OneToOne
    @JoinColumn(name="subject_id")
    public Subject subject;
    public Enrolment() {

    }

    public void setMember(Member member) {
        this.member = member;
        member.getEnrolments().add(this);
    }

}

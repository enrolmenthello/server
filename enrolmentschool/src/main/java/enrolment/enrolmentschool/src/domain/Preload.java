package enrolment.enrolmentschool.src.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public  class Preload {

    @Id @GeneratedValue
    @Column(name="preload_id")
    private Long enrolmentId;
    private String subjectProfessor;
    private int subjectNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private EnrolmentStatus status;

    public Preload(){

    }

    public void setMember(Member member) {
        this.member = member;
        member.getPreloads().add(this);
    }





}
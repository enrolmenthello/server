package enrolment.enrolmentschool.src.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
public  class Preload {

    @Id @GeneratedValue
    @Column(name="preload_id")
    private Long preloadId;
    private String name;

    private String professor;
    private LocalTime time;
    private int gradePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

   @OneToOne
    @JoinColumn(name="subject_id")
    private Subject subject;

    public Preload(){

    }

    public void setMember(Member member) {
        this.member = member;
        member.getPreloads().add(this);
    }
}
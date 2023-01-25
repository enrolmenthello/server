package enrolment.enrolmentschool.src.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="enrolment_subject")
@Getter @Setter
public class EnrolmentSubject {
    @Id @GeneratedValue
    @Column(name="enrolment_subject_id")
    private Long enrolmentSubejctId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="enrolment_id")
    private Enrolment enrolment;

    private int enrolmentGrade;
    private int count;

    public static EnrolmentSubject createEnrolmentSubject(Subject subject, int enrolmentGrade, int count){
      EnrolmentSubject enrolmentSubject=new EnrolmentSubject();
      enrolmentSubject.setSubject(subject);
      enrolmentSubject.setEnrolmentGrade(enrolmentGrade);
      enrolmentSubject.setCount(count);

      subject.removeSubject(count);
      return enrolmentSubject;
    }

    public void cancel() {
    getSubject().addStock(count);
    }

    public int getTotalGrade() {
    return getEnrolmentGrade()*getCount();
    }
}

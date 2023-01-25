package enrolment.enrolmentschool.src.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="preload_subject")
@Getter @Setter
public class PreloadSubject {
    @Id @GeneratedValue
    @Column(name="preload_subject")
    private Long preload_subjectSubejctId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="preload_subject_id")
    private Enrolment preload_subject;

}
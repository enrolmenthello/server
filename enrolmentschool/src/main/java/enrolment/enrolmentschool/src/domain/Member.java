package enrolment.enrolmentschool.src.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")

    private Long id;

    private String name;
    private int password;
    private int studentId;

    @OneToMany(mappedBy = "member")
    private List<Enrolment> enrolments=new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Preload> preloads=new ArrayList<>();

}
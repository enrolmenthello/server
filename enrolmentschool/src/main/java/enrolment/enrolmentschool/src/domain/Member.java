package enrolment.enrolmentschool.src.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private String id;

    private String name;
    private String password;


    @OneToMany(mappedBy = "member")
    private List<Enrolment> enrolments=new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Preload> preloads=new ArrayList<>();

    public Member() {

    }
}
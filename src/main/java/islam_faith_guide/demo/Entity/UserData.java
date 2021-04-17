package islam_faith_guide.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class UserData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;


    private String firstName;
    private String lastName;
    private String userName;
    private String password;


//    @OneToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "id", nullable = false)
//
//    private MyContent myContent;

    @Transient
    private String msg;

    @Transient
    private int i;


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

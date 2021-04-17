package islam_faith_guide.demo.Entity;

import jdk.jfr.DataAmount;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class TestQuestions  {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "id")
    private Long Id;

    private String Question;

    private String Answer;

//    private String thirdTest;
}

package islam_faith_guide.demo.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TestAnswers {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    private String firstTestAns;
    private String secondTestAns;
    private String thirdTestAns;

}

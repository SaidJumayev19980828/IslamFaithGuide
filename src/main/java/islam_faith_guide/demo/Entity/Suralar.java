package islam_faith_guide.demo.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Data
public class Suralar
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 1000000)
    private String audioUrl;

    @Column(length = 1000000)
    private String textUrl;


}

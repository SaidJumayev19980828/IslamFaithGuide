package islam_faith_guide.demo.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;
import java.io.Serializable;


@Getter
@Setter
@RequiredArgsConstructor
public class Condition1  implements Serializable {

    @Transient
    private String msg;

    private String userName;
    private String password;

    @Transient
    private int i;
}

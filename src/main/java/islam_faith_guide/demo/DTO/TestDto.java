package islam_faith_guide.demo.DTO;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class TestDto implements Serializable {

    private String firstTest;
    private String secondTest;
    private String thirdTest;

}

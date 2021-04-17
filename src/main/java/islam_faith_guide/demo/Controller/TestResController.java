package islam_faith_guide.demo.Controller;

import islam_faith_guide.demo.Entity.TestResult;
import islam_faith_guide.demo.Repository.TestResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("testRes")
public class TestResController {

    @Autowired
    TestResultRepo testResultRepo;

    @PostMapping("post")
    public ResponseEntity<TestResult>postResult( @RequestBody TestResult testResult)
    {
        return ResponseEntity.status(HttpStatus.OK).body(testResultRepo.save(testResult));
    }

    @GetMapping("get")
    public ResponseEntity<List<TestResult>> getResult(TestResult testResult, String name)
    {
        name=testResult.getUserName();
     return ResponseEntity.status(HttpStatus.OK).body(testResultRepo.findByUserName(name)) ;
    }

}

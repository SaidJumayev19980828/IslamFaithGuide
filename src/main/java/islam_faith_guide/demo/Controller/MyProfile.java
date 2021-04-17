package islam_faith_guide.demo.Controller;


import islam_faith_guide.demo.Entity.MyContent;
import islam_faith_guide.demo.Repository.MyContentRepo;
import islam_faith_guide.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myprofile")
public class MyProfile {
    @Autowired
    MyContentRepo myContentRepo;

    @Autowired
    UserRepo userRepo;

    @PostMapping("savedcontent")
    public void savedPosts(@RequestBody MyContent myContent)
    {

        myContentRepo.save(myContent);

    }
    @GetMapping("myContent")
    public ResponseEntity<List<MyContent>>myCont(MyContent myContent)
    {

        return ResponseEntity.status(HttpStatus.OK).body(myContentRepo.findAll());
    }




}

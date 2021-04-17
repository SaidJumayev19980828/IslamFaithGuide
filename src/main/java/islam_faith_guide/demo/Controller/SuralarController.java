package islam_faith_guide.demo.Controller;

import islam_faith_guide.demo.Entity.Suralar;
import islam_faith_guide.demo.Repository.SuraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sura")
public class SuralarController {
    @Autowired
    SuraRepo suraRepo;

    @GetMapping
    public List<Suralar>getSuralar(Suralar suralar)
    {
        return suraRepo.findAll();
    }

}

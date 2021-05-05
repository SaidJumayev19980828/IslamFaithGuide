package islam_faith_guide.demo.Service;


import islam_faith_guide.demo.DTO.Root1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(value = "city", url = "https://api.pray.zone/v2/times/month.json?city=&month=")

public interface PrayersTimeService {

    @GetMapping()
     Root1 getPosts(@RequestParam ("city")String city,@RequestParam("month")String month);




}

package islam_faith_guide.demo.Controller;

import com.github.javafaker.Faker;
import islam_faith_guide.demo.DTO.Example;
import islam_faith_guide.demo.DTO.TestDto;
import islam_faith_guide.demo.Entity.TestQuestions;
import islam_faith_guide.demo.Repository.ShowTestsRepo;
import islam_faith_guide.demo.Repository.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("test")
public class Test {

        @Autowired
        TestRepo testRepo;

        @Autowired
       ShowTestsRepo showTestsRepo;

        int index;
         int size;
         Long idx;
         int i=0;
        Object a2;

        @GetMapping("show")
        public List<String>  tests(TestDto testDto, TestQuestions testQuestions)
        {
            Faker faker=new Faker();
            Faker faker1=new Faker();


            Long a=showTestsRepo.count();

            for(int i=0;i<5;i++)
            {
                faker.number().numberBetween(1,15);
                faker1.number().numberBetween(1,15);


            }

            System.out.println("TITLE"+ faker.number().numberBetween(0,15));

               List<String>list=new ArrayList<>();

                 list.add(0, String.valueOf(showTestsRepo.findById(Long.valueOf(String.valueOf(Long.valueOf(faker.number().numberBetween(1,3)))))));
                 list.add(1, String.valueOf(showTestsRepo.findById(Long.valueOf(String.valueOf(Long.valueOf(faker.number().numberBetween(3,6)))))));
                 list.add(2, String.valueOf(showTestsRepo.findById(Long.valueOf(String.valueOf(Long.valueOf(faker.number().numberBetween(6,9)))))));
                 list.add(3, String.valueOf(showTestsRepo.findById(Long.valueOf(String.valueOf(Long.valueOf(faker.number().numberBetween(9,12)))))));
                 list.add(4, String.valueOf(showTestsRepo.findById(Long.valueOf(String.valueOf(Long.valueOf(faker.number().numberBetween(12,15)))))));
            List<String>filteredList =list.stream()
                    .collect(Collectors.toList());

            List str =filteredList;
            List arrOfStr = str.subList(0,5);
            for (Object a1 : arrOfStr)
            {
                System.out.println("AAA"+a1);
               a2=a1;
            }
            Example example=new Example();
            System.out.println("A2"+a2);


            return filteredList;
        }


    }



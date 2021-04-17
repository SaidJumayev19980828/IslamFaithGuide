package islam_faith_guide.demo.Controller;


import islam_faith_guide.demo.DTO.UserDto;
import islam_faith_guide.demo.Entity.Condition1;
import islam_faith_guide.demo.Entity.UserData;
import islam_faith_guide.demo.Repository.UserRepo;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@RestController
@RequestMapping("user")
public class UserController {
    String test;
    @Autowired
    UserRepo userRepo;
    int a;


    @PostMapping("reg")
    public ResponseEntity<UserDto> userRegister(@RequestBody UserData userData, UserDto userDto)
    {
         String name=userData.getUserName();
        System.out.println("UserName"+userData.getUserName());
        if(userRepo.findByUserName(name).isPresent())
        {
            System.out.println("User already exist");
            test="User already exist";

            userData.setMsg(test);
             a=1;
            userData.setI(a);


        }
        else
        {
            System.out.println("User created");
            userRepo.save(userData);
            test="User created";
            a=0;
            userData.setI(a);

        }
            userDto.setMsg(test);
            userDto.setFirstName(userData.getFirstName());
            userDto.setLastName(userData.getLastName());
            userDto.setPassword(userData.getPassword());
            userDto.setUserName(userData.getUserName());
            userDto.setI(a);

        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }
    @GetMapping("login")
    public ResponseEntity<Condition1>userLogin(UserData userData, UserDto userDto, Condition1 condition1)
    {
        userDto.setFirstName(userData.getFirstName());

        String name=userData.getUserName();

        String password=userData.getPassword();

        if(userRepo.findByUserName(name).isPresent()&&userRepo.findByPassword(password).isPresent())
        {

            System.out.println("test"+name);


                test="Logged successfully";
                a=1;
        }
        else
        {
            System.out.println("User not found"+name);
            System.out.println("password"+password);
            test="User not found ";
            a=0;

        }
        condition1.setUserName(userData.getUserName());
        condition1.setMsg(test);
        condition1.setPassword(userData.getPassword());
        condition1.setI(a);
        return ResponseEntity.status(HttpStatus.OK).body(condition1);
    }



}

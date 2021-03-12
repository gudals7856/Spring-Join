package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // http://localhost:8080/ 입력했을 때 첫번째 호출되는 것
    @GetMapping("/")
    public String home(){
        return "home";
    }
}

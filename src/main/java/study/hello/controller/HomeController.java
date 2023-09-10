package study.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 요청이 오면 첫번째로 스프링 컨테이너 안에 있는 컨트롤러 먼저 찾고 없으면 static파일을 찾는다
    public String home(){
        return "home";
    }
}

package study.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){

        model.addAttribute("data","hello");

        return "helloPage";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){//required = false : param이 없어도 정상 동작
        model.addAttribute("name",name);//http://localhost:8080/hello-mvc?name=ggg : name = ggg -http get방식
        return "hello-template";

    }

    @GetMapping("hello-string")
    @ResponseBody// : http의 헤더부와 바디부중 바디부에 return을 직접 넣어줌
    public String helloString(@RequestParam("name") String name){
        //return "<html>hello " + name+"</html>";//html 방식
        return "hello " + name;
    }
}

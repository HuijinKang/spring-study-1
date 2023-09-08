package study.hello.controller;

import org.springframework.stereotype.Controller;
import study.hello.service.MemberService;

// 스프링을 실행 -> 스프링 컨테이너 생성 -> @Controller가 있으면 MemberController객체를 생성해서 스프링에 넣는다 -> 스프링이 관리
@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService(); // new를 통해 객체를 여러 개 생성할 필요 없이 공용으로 사용

}

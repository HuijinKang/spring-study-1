package study.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import study.hello.domain.Member;
import study.hello.service.MemberService;

import java.util.List;

// 스프링을 실행 -> 스프링 컨테이너 생성 -> @Controller가 있으면 MemberController객체를 생성해서 스프링에 넣는다 -> 스프링이 관리
@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService(); // new를 통해 객체를 여러 개 생성할 필요 없이 공용으로 사용
    private MemberService memberService;

    /*@Autowired // 필드 주입, 단점 : 바꿀 수 없다
    private MemberService memberService;*/

    /*@Autowired // setter주입, 단점 : 항상 public으로 열려 있어야 한다
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }*/

    @Autowired // Controller를 Service와 이어줌 : DI 의존관계주입, 생성자 주입, 추천
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        // memberService.setMemberRepository(); setter주입은 아무에게나 열려있어서 추천안함
        System.out.println("memberService.getClass() = " + memberService.getClass()); // 프록시 확인
    }

    @GetMapping("/members/new")
    public  String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}

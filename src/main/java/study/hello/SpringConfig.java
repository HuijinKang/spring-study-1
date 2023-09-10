package study.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.hello.aop.TimeTraceAop;
import study.hello.repository.*;
import study.hello.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 자바 코드로 spring bean을 동록하기
@Configuration
public class SpringConfig {

   /* private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired // 생성자가 하나인 경우엔 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean // sprig bean에 등록
    public MemberService memberService(){
        return new MemberService(memberRepository); // spring bean에 등록되어 있는 memberRepository를 MemberService에 넣어준다.
    }

    /*@Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository(); // 구현 클래스를 new return
        // return new JdbcMemberRepository(dataSource);
        // return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }*/

    //@Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}

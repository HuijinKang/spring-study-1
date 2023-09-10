package study.hello.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import study.hello.domain.Member;
import study.hello.repository.MemberRepository;
import study.hello.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional /* 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고,테스트 완료 후에 항상 롤백한다.
이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.*/
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    //@Commit // 메소드가 끝나면 commit함.
    void 회원가입() { // 테스트 코드는 한글로 적어도 괜찮음
        //given : 상황이 주어졌을 때
        Member member = new Member();
        member.setName("spring22"); // spring 을 입력하고 memberRepository를 클리어 하지 않을 시 이미 있는 값이기 때문에 오류 발생

        //when : 이것을 실행했을 때
        Long saveId = memberService.join(member);

        //then : 원하는 결과가 나와야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()); // 이름 검증 member.getName() == findMember.getName()
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // command + option + v

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}
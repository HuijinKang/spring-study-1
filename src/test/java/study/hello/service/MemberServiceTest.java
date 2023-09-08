package study.hello.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.hello.domain.Member;
import study.hello.repository.MemoryMemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 각 테스트가 실행되기 전에 실행되는 전처리기
    public void beforeEach() { // 객체 생성
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 테스트가 실행된 후에 실행되는 후처리기, 메서드가 끝날때마다 이 메서드가 호출
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { // 테스트 코드는 한글로 적어도 괜찮음
        //given : 상황이 주어졌을 때
        Member member = new Member();
        member.setName("hello"); // spring 을 입력하고 memberRepository를 클리어 하지 않을 시 이미 있는 값이기 때문에 오류 발생

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

       /* try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
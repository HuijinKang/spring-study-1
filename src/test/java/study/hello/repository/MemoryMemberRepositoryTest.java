package study.hello.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import study.hello.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 끝날때마다 이 메서드가 호출, 콜백 메소드
    public void afterEach(){ // 테스트가 끝날 떄마다 레포지토리를 지워주는 메소드
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findByName(member.getName()).get(); // Optional에서 꺼낼 때 .get()을 사용
        System.out.println("(result == member) = " + (result == member));
        //Assertions.assertEquals(member, result); // 같은 지 확인, result 대신 null이 들어가면 빨간불 에러
        assertThat(member).isEqualTo(result); // static import를 통해 클래스명 없이 메소드 호출 가능
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift+f6 변수명 한번에 바꿈
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);//member2를 비교하면 오류
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void makeStar(){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("*");
        }
    }
}

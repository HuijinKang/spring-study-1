package study.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.hello.domain.Member;

import java.util.Optional;

// 스프링 데이터 jpa가 repository를 보고 스프링 빈을 자동으로 만들고 객체를 프록시라는 기술로 생성 스프링 빈에 올림.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
    // ex) Optional<Member> findByNameAndId(String name, Long id);
}

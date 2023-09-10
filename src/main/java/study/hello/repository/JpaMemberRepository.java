package study.hello.repository;

import study.hello.domain.Member;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    // jpa 라이브러리를 받으면 springBoot가 database랑 연결, properties 세팅 정보, database connection정보 등을 합쳐 EntityManager를 자동 생성한다.
    // 내부적으로 datasource 들고 있어서 db랑 통신하는 걸 내부에서 처리

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList(); // jpql 쿼리언어
        // command+option+n : inline
    }
}

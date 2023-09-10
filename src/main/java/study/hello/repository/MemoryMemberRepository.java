package study.hello.repository;

import org.springframework.stereotype.Repository;
import study.hello.domain.Member;

import java.util.*;

/*@Repository*/
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(store.get(id)); //null이 반환될 가능성이 있으면 Optional로 감싼다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //루프를 다 돌면서 찾아지면 반환, 끝까지 돌려도 없으면 Optional에 null이 포함되어서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // values = member들
    }

    public void clearStore(){
        store.clear(); //데이터를 지움
    }

}

package study.hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.hello.domain.Member;
import study.hello.repository.MemberRepository;
import study.hello.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

/*@Service*/
@Transactional // jpa를 쓰려면 Transactional이 필요하다, jpa는 데이터 변경이 Transactional 안에서 실행되어야 한다.
public class MemberService {

    // 클래스에서 command+shift+t => test 생성
    private MemberRepository memberRepository;

    /*@Autowired // setter주입
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /*@Autowired // DI : 직접 new를 통해 생성하지 않고 외부에서 memberRepository를 외부에서 넣어줌, 스프링 컨테이너에 올라가는 것들만 Autowired가 동작
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 생성자를 통해 외부에서 memberRepository필드에 객체를 넣어준다*/

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * */
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);//중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다");
//        });

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}

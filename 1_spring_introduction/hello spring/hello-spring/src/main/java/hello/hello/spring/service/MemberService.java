package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // private final MemberRepository memberRepository; = new MemoryMemberRepository(); 라고 하지않고
    // 아래처럼 생성자로 처리해주면 Test파일에서 또 따로 memberRepository 인스턴트 안만들어도 됨
    // Dependency Injection
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     * */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

//        long start = System.currentTimeMillis();
//
//        try{
//            //같은 이름이 있는 중복 회원 x
//            validateDuplicateMember(member);
//            memberRepository.save(member);
//            return member.getId();
//        }finally{
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = "+timeMs+"ms");
//        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {  //기존에는 result != null
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();

//        long start = System.currentTimeMillis();
//
//        try{
//            return memberRepository.findAll();
//        }finally{
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers = "+timeMs+"ms");
//        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}


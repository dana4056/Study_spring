package hello.core.member;

//인터페이스에 구현체가 하나만 있으면 관례상 'Impl' 붙여줌

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //추상(인터페이스)에만 의존 OCP, DIP 원칙 준수

    @Autowired  //의존관계 자동주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        //생성자 통해서 구현체 지정
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

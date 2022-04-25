package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //-----------------------------------------------------------------------------------
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 위의 코드 OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수한것 처럼 보이지만 위배
    // 인터페이스인 discountPolicy에도 의지하고, 구체적인 구현체인 RateDiscountPolicy에도 의존!!
    // 클라이언트인 이 스크립트(OrderServiceImpl)를 수정해야 하니까 OCP위반
    //-----------------------------------------------------------------------------------
    private final MemberRepository memberRepository;  //final 있으면 기본 할당이든 생성자 통해서든 값 할당해야함
    private final DiscountPolicy discountPolicy;  //OCP, DIP 원칙 준수 (추상(인터페이스)에만 의존)

    @Autowired  // 의존관계 주입: 생성자 주입 - 해당 스프링빈에서 생성자 하나이면 @Autowired 생략 가능
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    Lombok의 @RequiredArgsConstructor 사용하면 final 붙은 필드를 기준으로 생성자 자동 생성
//    따라서 위의 코드처럼 생성자 안만들어도 됨


//    @Autowired // 의존관계 주입: 수정자 주입 - setter(final 필드면 안됨)
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
//    @Autowired // 의존관계 주입: 수정자 주입 - setter(final 필드면 안됨)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        this.discountPolicy = discountPolicy;
//    }

//    @Autowired private MemberRepository memberRepository;   // 의존관계 주입: 필드 주입 (잘 안씀)
//    @Autowired private DiscountPolicy discountPolicy;       // 의존관계 주입: 필드 주입 (잘 안씀)

//    @Autowired  // 의존관계 주입: 일반 메서드 주입 (잘 안씀)
//    public void init(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

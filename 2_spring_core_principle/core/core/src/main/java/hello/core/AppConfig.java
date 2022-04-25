package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //스프링 컨테이너에 등록(CGLIB이라는 바이트코드 조작 메소드 통해서 각 메소드를 상속받는 자식메소드 만들어 등록)
public class AppConfig { //역할과 구현이 다 들어나도록 작성

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // memberService와 orderService를 호출하면 new로 MemoryMemberRepository를 호출하는데, 싱글톤 깨지는것 아닌가?
    // : ConfigurationSingletonTest 가서 확인
    @Bean
    public MemberService memberService(){
        //생성자 매개변수 통해서 구현체 지정(생성자 주입)
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

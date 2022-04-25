package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출할 때 마다 객체를 생성하는지 확인
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성하는지 확인
        MemberService memberService2 = appConfig.memberService();

        //참조값 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);


        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // new SingletonService(); 컴파일 오류 발생(생성자가 private이기 때문에 외부에서 new로 인스턴스 생성 금지)
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService1 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // ********** isSameAs와 isEqualTo 비교 **********
        // same은 자바에서 '=='비교 (참조(주소값)를 비교함, 객체의 인스턴스가 같은지)
        // equal 자바에서 equals 비교?
    }

    @Test
    @DisplayName("스프링 컨테이터와 싱글톤")
    void springContainer(){

        //AppConfig appConfig = new AppConfig();
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회: 호출할 때 마다 객체를 생성하는지 확인
        MemberService memberService1 = ac.getBean("memberService", MemberServiceImpl.class);

        //2. 조회: 호출할 때 마다 객체를 생성하는지 확인
        MemberService memberService2 = ac.getBean("memberService", MemberServiceImpl.class);;

        //참조값 다른 것 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2 같을 것이야~~
        assertThat(memberService1).isSameAs(memberService2);
    }
}

package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        int orderPriceA= statefulService1.order("userA", 10000);
        //ThreadB: B사용자가 20000원 주문
        int orderPriceB = statefulService2.order("userB", 20000);

        //사용자 A가 주문금액 조회
        //int price = statefulService1.getPrice();
        System.out.println("price = " + orderPriceA);
        System.out.println("price = " + orderPriceB);

        //assertThat(statefulService1.getPrice()).isNotEqualTo(10000);

    }


    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
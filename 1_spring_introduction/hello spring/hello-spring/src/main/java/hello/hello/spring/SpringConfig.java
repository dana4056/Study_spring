package hello.hello.spring;

import hello.hello.spring.aop.TimeTraceAop;
import hello.hello.spring.repository.*;
import hello.hello.spring.service.MemberService;
import hello.hello.spring.repository.JdbcMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//자바코드로 스프링빈 등록하기
@Configuration
public class SpringConfig {
    //컨트롤러는 어쩔 수 없이 @Controller 해줘야함

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }


// ------------- JPA 사용시 필요 -------------
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
// -----------------------------------------

// ------------------ spring data JPA 사용시 필요 ------------------
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
// ---------------------------------------------------------------

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

// ---------------------- spring data jpa 쓸 떈 필요ㄴㄴ ----------------------
//    @Bean
//    public MemberRepository memberRepository(){   // MemberRepository는 인터페이스
//                                                  // MemoryMemberRepository는 구현체, 인터페이스에는 new 못씀
//        // return new MemoryMemberRepository();         // 그냥 메모리를 디비로
//        // return new JdbcMemberRepository(dataSource); // 순수 JDBC방법
//        // return new JdbcTemplateMemberRepository(dataSource); // JDBC Template 방법
//        // return new JpaMemberRepository(em);   // JPA 사용
//
//    }
// ---------------------------------------------------------------------------

}

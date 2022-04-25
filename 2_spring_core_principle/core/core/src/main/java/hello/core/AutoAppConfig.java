package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // @Component 붙은 클래스들을 스프링빈으로 등록시키는데?, filter적용해서 제외시킬 수 있음
        // 컴포넌트 스캔을 사용하면 @Configuration 이 붙은 설정 정보도 자동으로 등록
        // @Configuration + @Bean 조합은 수동으로 등록시키는 방법인데 자동으로 또 등록하면 중복 발생
        // (이전에 실습했던 AppConfig 삭제 안해서 그럼)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    // 수동 빈 드록 vs 자동 빈 등록에서 빈 이름이 중복되어 충돌하였을 때
//    // 수동 빈 등록이 우선권 가져 수동 빈이 자동 빈을 오버라이딩 함
//    // 하지만!!!!!! 애매한 짓 하지마라!!!!!!!!
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}

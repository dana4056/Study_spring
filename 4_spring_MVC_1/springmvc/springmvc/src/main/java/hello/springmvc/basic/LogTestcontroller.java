package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestcontroller {

    // 롬복(@Slf4j) 사용하면 생략 가능
    // private final Logger log = LoggerFactory.getLogger(getClass()); //클래스 지정

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        //로그 찍을 때 어떤 상태의 레벨인지 지정 가능
        //디폴트로 운영서버는 info부터(info, warn, error) 출력
        log.trace("trace log = {}", name);
        log.debug("debug log = {}", name); // 디버그 할 때 봄(개발서버 같은 곳에서)
        log.info("info log = {}", name);   // 중요한 정보야 (비즈니스 로직이나 OS에서도 봐야하거나)
        log.warn("warn log = {}", name);   // 경고
        log.error("error log = {}", name); // 에러
        return "ok";
    }
}

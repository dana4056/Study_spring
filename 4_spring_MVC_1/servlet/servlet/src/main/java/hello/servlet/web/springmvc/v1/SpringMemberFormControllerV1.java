package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")  //핸들러매핑과 핸들러어댑터 조회 될 것
    public ModelAndView process() {
        return new ModelAndView("new-form");  //DispatcherServlet으로 ModelAndView 전달
    }
}

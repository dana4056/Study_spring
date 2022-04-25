package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello") // /hello로 오면 해당 서블릿 실행
public class HelloServlet extends HttpServlet {

    @Override  // 서블릿 호출되면 service 메서드 호출됨
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 쿼리 파라미터 가져오기
        String username = request.getParameter("username");
        System.out.println("username =" + username);

        response.setContentType("text/plain");  // header 정보에 들어감
        response.setCharacterEncoding("utf-8"); // header 정보에 들어감
        response.getWriter().write("hello " + username);
    }
}

package hello.servlet.basic.response;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        //content 와 charset 지정
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        redirect(response);
        response.getWriter().write("안녕하세요");
    }

    //redirect
    private void redirect(HttpServletResponse response) throws IOException{

        response.sendRedirect("/basic/hello-form.html");
    }
}

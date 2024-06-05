package himedia;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Tomcat으로부터 요청 객체를 받아 출력한다.
		resp.setContentType("text/html; charset=UTF-8");
		
//		사용자로부터 name 파라미터를 전달받아서 출력
//		파라미터로 데이터가 전달되는 GET 방식의 요청을 처리하는 method
		String name = req.getParameter("name");
		
		if(name == null) {
			name = "???";
		}		
//		super.doGet(req, resp);
		PrintWriter out = resp.getWriter();
		
		out.println("<h1>Hello Servlet</h1>");
		out.println("<p>안녕하세요,"+name+"님</p>");
		
	}

}

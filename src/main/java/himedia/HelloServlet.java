package himedia;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.logging.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("HelloServlet");
	private String appName;
	private String dbUser;
	private String dbPass;
	
//	Servlet이 처음 호출될 때
	@Override
	public void init(ServletConfig config) throws ServletException {
		logger.info("LifeCycle : init");
		super.init(config);
		
//		Context Parameter 받아오기
		ServletContext servletContext = getServletContext();
		
		
		appName = servletContext.getInitParameter("appName");
		dbUser = servletContext.getInitParameter("dbUser");
		dbPass = servletContext.getInitParameter("dbPass");
		
		logger.info("DBUSER:"+dbUser);
		logger.info("DBPASS:" + dbPass);
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("LifeCycle : service");
		super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("LifeCycle : doGet");
//		Tomcat으로부터 요청 객체를 받아 출력한다.
		resp.setContentType("text/html; charset=UTF-8");
		
//		사용자로부터 name 파라미터를 전달받아서 출력
//		파라미터로 데이터가 전달되는 GET 방식의 요청을 처리하는 method
		String name = req.getParameter("name");
		
		if(name == null) {
			name = "???";
		}		
		
//		Servlet Parameter 받아오기
		ServletConfig config = getServletConfig();
		String servletName = config.getInitParameter("servletName");
		String description = config.getInitParameter("description");
		
//		super.doGet(req, resp);
		PrintWriter out = resp.getWriter();
		
		out.println("<h1>App Name:"+appName + "</h1>");
		
		out.println("<h2>"+servletName +"</h2>");
		out.println("<p>"+description+"</p>");
		
		out.println("<p>안녕하세요,"+name+"님</p>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("LifeCycle : doPost");
//		폼으로부터 넘어온 error Checkbox가 체크가 되어 있으면 예외 발생
//		web.xml의 error-page 노드 테스트를 위함
		
//		if(req.getParameter("error").equals("on")) {
		
			// ==> check를 해제하면, value는 null. null은 equals가 성립이 안되므로 
			// => NullPointerException . 따라서 null을 절대 가질수 없는 객체롤 앞에.
		if("on".equals(req.getParameter("error"))) {
			throw new ServletException("에러 페이지 테스트");
		}
		
//		클라이언트의 form으로부터 전달 받은 데이터를 목록 출력
		resp.setContentType("text/html;charset=UTF-8");
		
//		응답 객체로부터 writer를 얻어온다.
		PrintWriter out = resp.getWriter();
		out.println("<h1>폼 데이터 처리</h1>");
		
		out.println("<p>폼으로부터 전송된 데이터</p>");
		
//		폼으로부터 전송된 파라미터명 확인
//		
		Enumeration<String> params = req.getParameterNames();
		out.println("<ul>");
		while(params.hasMoreElements()) {
			String pName = params.nextElement();
			out.printf("<li>%s=%s</li>%n",pName,req.getParameter(pName));
		}
		out.println("<ul>");
	}


	@Override
	public void destroy() {
		logger.info("LifeCycle : destory");
		super.destroy();
	}
	
	

}

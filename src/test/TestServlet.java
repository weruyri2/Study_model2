package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          System.out.println("doGet() 호출");
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         System.out.println("doPost() 호출");
	
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	    System.out.println("service() 호출");
	    // 서블릿이 처리할 동작 -doGet/doPost 
	
	}

	@Override
	public void destroy() {
		 System.out.println(" destroy() 호출 ");
		 // 서블릿 종료 
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init() 호출");
		// 서블릿 초기화 메서드 
		
		
	}
	
	
	

}

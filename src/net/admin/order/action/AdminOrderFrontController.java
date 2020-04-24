package net.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.order.action.OrderDetailAction;



public class AdminOrderFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doProcess!!");
		
		// 가상주소를 비교해서 처리 

		// 가상주소의 정보를 가져오기
		String requestURI = request.getRequestURI();
		System.out.println("URI : "+requestURI);
		
		//String requestURL = request.getRequestURL().toString();
		//System.out.println("URL : "+requestURL);
		
		// 프로젝트명을 가져오기 
		String contextPath = request.getContextPath();
		System.out.println("ContextPath : "+contextPath);
		
		// 실제 변경되는 가상주소
		String command = requestURI.substring(contextPath.length());
		System.out.println("Command : "+command);
		System.out.println("----------주소계산 완료-------------------");
		
		
		
		
		//////////////////////////////////////////////////////////////////
		// 계산한 가상주소와  특정페이지가 같으면 
		// 페이지의 동작에 따라서 이동 
		
		// 처리 페이지정보 객체 (인터페이스-execute())
		Action action = null;
		// 페이지 이동정보 저장 객체 
		ActionForward forward = null;
		
		
		// 처리페이지
		if(command.trim().equals("/AdminOrderList.ao")){
			//=>사용자 모두가 주문한 상품정보 리스트 확인
			// (관리자 전용 주문리스트)
			System.out.println("AdminOrderList.ao 처리 (model->view)");
			
			action = new AdminOrderListAction();
			
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(command.equals("/AdminOrderDetail.ao")){
			System.out.println("AdminOrderDetail.ao주소 요청");
			
			action = new AdminOrderDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminOrderModify.ao")){
			System.out.println("AdminOrderModify.ao주소 요청");
			
			action = new AdminOrderModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminOrderDelete.ao")){
			System.out.println("AdminOrderDelete.ao주소 요청");
			
			action = new AdminOrderDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		// 페이지 이동처리 
		if(forward != null){ // 페이지 이동정보가 있을때 
			
			// 페이지 이동  sendRedirect/forward
			if(forward.isRedirect()){ // true
				response.sendRedirect(forward.getPath());
			}else{ // false
			   RequestDispatcher dis =
					   request.getRequestDispatcher(forward.getPath());
			   
			   dis.forward(request,response);				
			}		
		}

		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet!!");
		doProcess(request, response);
	
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost!!");
		doProcess(request, response);
		
	}
}

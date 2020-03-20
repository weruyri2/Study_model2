package net.admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminGoodsFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doProcess() 호출!");
		
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
		/////////////////////////////////////////////////////////////////
		////2.가상 페이지 이동 동작 (model/view)
		/////////////////////////////////////////////////////////////////
		System.out.println("---------------페이지 구분 (view/model)");
		
		if(command.equals("/GoodsAdd.ag")){
			//관리자가 상품등록 페이지 (view)
			// ./admingoods/admin_goods_write.jsp
			System.out.println("/GoodsAdd.ag 처리완료 (view 이동)");
			
			// 이동정보 처리객체
			forward = new ActionForward();
			forward.setPath("./admingoods/admin_goods_write.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/GoodsAddAction.ag")){
			// 관리자가 상품등록한 페이지 처리 (model)
			System.out.println("/GoodsAddAction.ag 처리완료 (model 이동)");
			// GoodsAddAction() 객체 생성
			action = new GoodsAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		
		///////////////////////////////////////////////////////////////
		//////3. 실제 페이지 이동 동작 (redirect/forward)
		/////////////////////////////////////////////////////////////////
		System.out.println("---------페이지 이동 (redirect/forward)");
		
		if(forward != null){
			//if(forward.isRedirect() == true) {
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				
			}
		}
		
		
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet() 호출!");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		System.out.println("doPost() 호출!");
		doProcess(request, response);
	}

	
}

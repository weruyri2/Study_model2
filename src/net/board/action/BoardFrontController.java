package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess 호출!");

		////////////////////////////////////////////////////////////
		// 1. 가상주소 계산
		////////////////////////////////////////////////////////////

		String requestURI = request.getRequestURI();
		System.out.println("URI : " + requestURI);

		String contextPath = request.getContextPath();
		System.out.println("ContextPath(프로젝트명) : " + contextPath);

		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);

		System.out.println("----------페이지 주소 계산 완료----------------------");

		////////////////////////////////////////////////////////////
		// 2. 계산된 주소를 사용해서 페이지 형태구분(View/Model)
		////////////////////////////////////////////////////////////
		System.out.println("----------페이지 구분 (view/model)-----------------");

		Action action = null;
		ActionForward forward = null;

		if (command.equals("/BoardWrite.bo")) {
			// 글쓰기 페이지로 이동 (입력페이지) view
			// ./board/writeForm.jsp
			System.out.println("/BoardWrite.bo 주소 처리(view 페이지 이동)");

			forward = new ActionForward();

			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/BoardWriteAction.bo")) {
			// action 처리 (model 객체생성 처리)
			System.out.println("/BoardWriteAction.bo 주소 처리(model 페이지 이동)");

			// BoardWriteAction() 객체 생성
			action = new BoardWriteAction();

			// 객체를 사용해서 execute() 호출
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardList.bo")) {
			// BoardListAction 객체 (model)
			System.out.println("/BoardList.bo 주소 처리(model 페이지 이동)");

			action = new BoardListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardContent.bo")) {
			// BoardContentAction 객체 (model)
			System.out.println("/BoardContent.bo 주소 처리(model 페이지 이동)");

			action = new BoardContentAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardUpdate.bo")) {
			// BoardUpdate() 객체 생성 (model)
			System.out.println("/BoardUpdate.bo 주소처리 (model 페이지 이동)");

			action = new BoardUpdate();
			try {

				forward = action.execute(request, response);

				// forward
				// = new BoardUpdate().execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (command.equals("/BoardUpdateAction.bo")) {
			// BoardUpdateAction() 객체 생성 (model)
			System.out.println("/BoardUpdateAction.bo 주소처리 (model 페이지 이동)");

			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		} else if (command.equals("/BoardDelete.bo")) {
			// 글삭제 페이지로 이동 (입력페이지) view
			// ./board/deleteForm.jsp
			System.out.println("/BoardDelete.bo 주소 처리(view 페이지 이동)");
			
			forward = new ActionForward();
			forward.setPath("./board/deleteForm.jsp");
			forward.setRedirect(false);		
			
		} else if(command.equals("/BoardDeleteAction.bo")){
			// BoardDeleteAction() 객체를 생성
		
			System.out.println("/BoardDeleteAction.bo 주소 처리(model 페이지 이동)");
			
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReWrite.bo")){
			// 답글달기 페이지로 이동 (view)
			// ./board/reWriteBoard.jsp
			
			forward = new ActionForward();
			forward.setPath("./board/reWriteBoard.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/BoardReWriteAction.bo")){
			// 답글달기 처리 페이지로 이동(model)
			// BoardReWriteAction() 객체 생성
			System.out.println("/BoardReWriteAction.bo 주소 처리 (model 이동)");
			
			action = new BoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.trim().equals("/FileBoardWrite.bo")){
			// 파일 업로드 글쓰기 처리  (view 이동)
			// ./board/fileBoardWrite.jsp
			
			forward = new ActionForward();
			forward.setPath("./board/fileBoardWrite.jsp");
			forward.setRedirect(false);			
		}else if(command.equals("/FileBoardWriteAction.bo")){
			//파일업로드 처리페이지 (model)
			// 파일의 데이터는 서버에 저장 (upload 가상폴더)
			// 파일의 이름만 DB에 저장
			System.out.println("/FileBoardWriteAction.bo 주소 처리 (model 이동)");
			
			action = new FileBoardWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		

		////////////////////////////////////////////////////////////
		// 3. 실제 페이지 이동 동작 (redirect/forward)
		////////////////////////////////////////////////////////////

		System.out.println("-----------페이지 이동(redirect(true)/forward(false))---------------");
		// 페이지 이동정보가 있을때만 페이지 이동

		if (forward != null) {
			if (forward.isRedirect()) {
				// true
				System.out.println("sendRedirect() 이동 : " + forward.getPath());
				response.sendRedirect(forward.getPath());
			} else {
				// false
				System.out.println("forward() 이동: " + forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet 호출!");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost 호출!");
		doProcess(request, response);
	}

}

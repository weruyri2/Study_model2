package net.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardDTO;

public class FileBoardWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FileBoardWriteAction() 실행");
		
		//1. 파일 업로드
		// 가상경로를 가져오기
		//업로드할 파일의 크기 지정
		// MultipartRequest 객체 생성 => 파일 업로드
		
		String realPath = request.getRealPath("/upload");
		//게시판글 저장
		System.out.println("물리적 경로 : " + realPath);
		
		//파일 최대크기
		int maxSize = 10*1024*1024; // 10MB
		
		// 파일 업로드 처리객체 생성
		MultipartRequest multi = new MultipartRequest(
					request,
					realPath,
					maxSize,
					"UTF-8",
					new DefaultFileRenamePolicy()
					);		
			
		// 파일 업로드 끝
		////////////////////////////////////////////

		// 전달된 정보 (이름, 제목, 파일)
		
		BoardDTO bdto = new BoardDTO();
		
		bdto.setName(multi.getParameter("name"));
		bdto.setPass(multi.getParameter("pass"));
		bdto.setSubject(multi.getParameter("subject"));
		bdto.setContent(multi.getParameter("content"));
		bdto.setFile(multi.getFilesystemName("file"));
						
		//2. 정보 DB에 저장
		//BoardDTO 객체 생성후
		//name,pass,subject,content,file,ip 저장 후 이동
		
		//BoardDAO 생성 -> 메서드 호출 insertBoard()
		
		//3. 페이지 이동(글 리스트)
		bdto.setIp(request.getRemoteAddr());
		///////////////////////////////////////
		//디비 처리객체 생성
		
		BoardDAO bdao = new BoardDAO();
		// 메서드 호출 : insertBoard(bb);
		bdao.insertBoard(bdto);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);		
		return forward;
	}

}

package net.board.db;

import java.nio.channels.FileLockInterruptionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비 연결(커넥션 풀 사용)
	private Connection getConnection() throws Exception {
		// Context 객체를 생성
		Context init = new InitialContext();

		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/model2DB");

		con = ds.getConnection();
		
		System.out.println(" 디비 연결 완료 : "+con);
		return con;
	}

	// 자원 해제

	public void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
			
			System.out.println(" 자원해제 완료 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// insertBoard(bdto)
    public void insertBoard(BoardDTO bdto){
    	System.out.println(" insertBoard(dto) 글쓰기 메서드 ");
    	int num = 0;
    	try {
    		// 1,2
			con = getConnection();
			
			// 3 sql ( 글번호 계산 -> 글쓰기 )
			sql ="select max(num) from model2_board";
			pstmt = con.prepareStatement(sql);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5 rs값비교 
			if(rs.next()){
				//num = rs.getInt(1)+1; // 인덱스 사용
				num = rs.getInt("max(num)")+1; // 컬럼명 사용
			}
			
			System.out.println(" 글번호 num : "+num);
			
			// sql (글쓰기)
			
			sql = "insert into model2_board "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bdto.getName());
			pstmt.setString(3, bdto.getPass());
			pstmt.setString(4, bdto.getSubject());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, 0); // 조회수 0
			pstmt.setInt(7, num); // re_ref 답글 그룹 (일반글 번호와 동일)
			pstmt.setInt(8, 0); //re_lev 답글 들여쓰기
			pstmt.setInt(9, 0); //re_seq 답글 순서
			pstmt.setString(10, bdto.getIp());
			pstmt.setString(11, bdto.getFile());
			
			// 실행 
			pstmt.executeUpdate();			
			
			System.out.println(" 글작성 완료 ! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    }
	// insertBoard(bdto)
	
	
    // getBoardCount()
    public int getBoardCount(){
    	int check = 0;
    	
    	try {
    		// 1,2
			con = getConnection();
			
			// 3 sql 구문작성
			sql = "select count(*) from model2_board";
			pstmt = con.prepareStatement(sql);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5 rs비교
			if(rs.next()){
				check = rs.getInt(1);
			}			
			System.out.println("게시판 글 개수 확인 완료 : "+check);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	
    	return check;
    }
    // getBoardCount()
    
    // getBoardList()
    public ArrayList getBoardList(){
    	// 가변길이 배열 생성 
    	ArrayList BoardList = new ArrayList();
    	
    	try {
    		// 1,2 디비연결 
			con = getConnection();
			
			// 3 sql (select)
			sql = "select * from model2_board";
			pstmt = con.prepareStatement(sql);
				    	
	    	// 4 실행
			rs = pstmt.executeQuery();
	    	
	    	// 5 rs 비교
			while(rs.next()){
				
				BoardDTO bdto = new BoardDTO();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getDate("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setIp(rs.getString("ip"));
				bdto.setName(rs.getString("name"));
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setSubject(rs.getString("subject"));
				
				// 글 하나의 정보를 ArrayList 한칸에 저장 
				BoardList.add(bdto);
				
			}// while()
			
		   System.out.println("게시판글 모두 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	
    	return BoardList;
    }
    // getBoardList()
	
    // getBoardList(startRow,pageSize)
    public ArrayList getBoardList(int startRow,int pageSize){
    	// 가변길이 배열 생성 
    	ArrayList BoardList = new ArrayList();
    	
    	try {
    		// 1,2 디비연결 
			con = getConnection();
			
			// 3 sql (select - 최신글이 위쪽 정렬,(답글순서 정렬), 10개씩 짤라서 가져감)
			sql = "select * from model2_board "
					+ "order by re_ref desc, re_seq asc "
					+ "limit ?,?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow - 1); // 시작행 -1
			pstmt.setInt(2, pageSize); // 가져갈 글의 개수 
			
				    	
	    	// 4 실행
			rs = pstmt.executeQuery();
	    	
	    	// 5 rs 비교
			while(rs.next()){
				
				BoardDTO bdto = new BoardDTO();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getDate("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setIp(rs.getString("ip"));
				bdto.setName(rs.getString("name"));
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setSubject(rs.getString("subject"));
				
				// 글 하나의 정보를 ArrayList 한칸에 저장 
				BoardList.add(bdto);
				
			}// while()
			
		   System.out.println("게시판글 모두 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	
    	return BoardList;
    }
    // getBoardList(startRow,pageSize)
	
	// updateReadCount(num)
    public void updateReadCount(int num){
    	
    	try {
    		// 1,2
			con = getConnection();
			
			// 3 sql 구문 (선택된 글 조회수를 1증가)
			sql = "update model2_board "
					+ "set readcount = readcount + 1 where num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// 4 실행 
			pstmt.executeUpdate();
			
			System.out.println(" 해당글("+num+") 조회수 1증가 ");
			
		}
//    	catch (NullPointerException e) {
//    		System.out.println(" 디비연결 오류 ");
//    		try {
//				con =  getConnection();
//			} catch (Exception e1) {
//				e1.printStackTrace();
//			}
//		}
    	catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    }
    // updateReadCount(num)
    
    // getBoard(num)
    public BoardDTO getBoard(int num){
    	BoardDTO bdto = null;
    	
    	try {
    		// 1,2
			con = getConnection();
			// 3 sql
			sql = "select * from model2_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5 rs 비교
			if(rs.next()){
				bdto = new BoardDTO();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getDate("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setIp(rs.getString("ip"));
				bdto.setName(rs.getString("name"));
				bdto.setNum(rs.getInt("num"));
				bdto.setPass(rs.getString("pass"));
				bdto.setRe_ref(rs.getInt("re_ref"));
				bdto.setRe_lev(rs.getInt("re_lev"));
				bdto.setRe_seq(rs.getInt("re_seq"));
				bdto.setReadcount(rs.getInt("readcount"));
				bdto.setSubject(rs.getString("subject"));
				
			}
			System.out.println(" 해당 정보 저장완료 : "+bdto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	
    	return bdto;
    }
    // getBoard(num)
    
    // updateBoard(bdto)
    public int updateBoard(BoardDTO bdto){
    	int check =-1;
    	
    	try {
    		// 1,2
			con = getConnection();
			// 3 sql
			sql = "select pass from model2_board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getNum());
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5 rs 비교 
			if(rs.next()){
				if(bdto.getPass().equals(rs.getString("pass"))){
					//3 글내용 수정
					sql ="update model2_board set name=?,subject=?,content=? where num=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, bdto.getName());
					pstmt.setString(2, bdto.getSubject());
					pstmt.setString(3, bdto.getContent());
					pstmt.setInt(4, bdto.getNum());
					
					// 4 실행
					pstmt.executeUpdate();
					check = 1;
					
				}else{// 비밀번호 오류
					check = 0;
				}
			}else{// 글이 없음
				check = -1;
			}
			
			System.out.println(" 정보수정 완료 : "+check);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
    	
    	return check;
    }
    // updateBoard(bdto)
    
    // deleteBoard(num,pass)
    public int deleteBoard(int num,String pass){
    	int check = -1;
    	
    	try {
			con = getConnection();
			// 삭제하고자 하는 글이 있는지 판단후 삭제 처리
			// 삭제 결과에 따라서 1,0,-1
			sql = "SELECT pass FROM model2_board WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))){
					sql = "DELETE FROM model2_board WHERE num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					pstmt.executeUpdate();
					
					check = 1;					
				}else{
					check = 0;
				}				
			}else{
				check = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}   	
    	
    	return check;
    }
    // deleteBoard(num,pass)
	
	//reInsertBoard(bdao)
    public void reInsertBoard(BoardDTO bdto){
    	int num = 0;
    	   	
    	try {
    		getConnection();
        	//1. 글번호 계산
			sql = "SELECT max(num) FROM model2_board";
    		pstmt = con.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		if(rs.next()){
    			num = rs.getInt(1)+1;
    		}
        	//2. 답글 순서 재배치
			sql ="UPDATE model2_board SET re_seq=re_seq+1 "
					+ "WHERE re_ref=? and re_seq>?";
        	pstmt = con.prepareStatement(sql);
        	pstmt.setInt(1, bdto.getRe_ref());
        	pstmt.setInt(2, bdto.getRe_seq());
    		pstmt.executeUpdate();
    		
    		
    		//3. 답글을 DB에 저장    	
			sql ="INSERT INTO model2_board("
					+ "num,name,pass,subject,content,readcount,"
					+ "re_ref,re_lev,re_seq,date,ip,file) "
					+ "values(?,?,?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bdto.getName());
			pstmt.setString(3, bdto.getPass());
			pstmt.setString(4, bdto.getSubject());
			pstmt.setString(5, bdto.getContent());
			pstmt.setInt(6, 0); // 조회수 0
			pstmt.setInt(7, bdto.getRe_ref()); // re_ref 답글 그룹 (일반글 번호와 동일)
			pstmt.setInt(8, bdto.getRe_lev()+1); //re_lev 답글 들여쓰기
			pstmt.setInt(9, bdto.getRe_seq()+1); //re_seq 답글 순서
			pstmt.setString(10, bdto.getIp());
			pstmt.setString(11, bdto.getFile());
			
			pstmt.executeUpdate();
			
			System.out.println("답글 작성 완료");
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			closeDB();
		}
    	

    	
    	
    	
    }
    //reInsertBoard(bdao)
	
}

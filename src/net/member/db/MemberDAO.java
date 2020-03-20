package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql="";
	
	// 디비 연결(커넥션 풀 사용)
	private Connection getConnection() throws Exception{
		// Context 객체를 생성
		Context init = new InitialContext();
		
		DataSource ds 
		 = (DataSource) init.lookup("java:comp/env/jdbc/model2DB");
		
		con = ds.getConnection();
		
		return con;
	}
	
	// 자원 해제 
	
	public void closeDB(){
		try {
			if(rs !=null) rs.close();
			if(pstmt !=null) pstmt.close();
			if(con !=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// insertMember(mb)
	public void insertMember(MemberBean mb){
		
		try {
			// 1,2
			con = getConnection();
			System.out.println(" DB 연결 성공 ");
			
			// 3 sql 작성
			sql="insert into model2_member "
					+ "values(?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getName());
			pstmt.setInt(4, mb.getAge());
			pstmt.setString(5, mb.getGender());
			pstmt.setString(6, mb.getEmail());
			pstmt.setTimestamp(7, mb.getReg_date());
			
			// 4 실행
			int check = pstmt.executeUpdate();
			
			if(check == 1){
				System.out.println(" 정상처리 ");
			}else{
				System.out.println(" 문제 발생( 데이터 저장 오류 ) ");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	// insertMember(mb)
	
	// idCheck(id,pass)
	public int idCheck(String id,String pass){
		int check = -1;
		
		try {
			// 1,2
			con = getConnection();
			
			// 3 sql
			sql = "select pass from model2_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5
			if(rs.next()){
               if(pass.equals(rs.getString("pass"))){
            	   // 아이디도 있고, 비밀번호 같음=> 회원(로그인성공)
            	   check = 1;            	   
               }else{
            	   // 아이디는 있지만, 비밀번호가 오류
            	   check=0;
               }
			}else{
				//회원아님
				check = -1;
			}
			
			System.out.println(" 아이디 체크 완료 : "+check);			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}	
		
		return check;
	}
	// idCheck(id,pass)
	
	// getMember(id)
	public MemberBean getMember(String id){
		MemberBean mb = null;
		
		try {
			// 1,2
			con = getConnection();
			// 3 sql
			sql = "select * from model2_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5 rs 비교
			if(rs.next()){
				// 회원정보를 저장 
				mb = new MemberBean();
				
				mb.setAge(rs.getInt("age"));
				mb.setEmail(rs.getString("email"));
				mb.setGender(rs.getString("gender"));
				mb.setId(rs.getString("id"));
				mb.setName(rs.getString("name"));
				mb.setPass(rs.getString("pass"));
				mb.setReg_date(rs.getTimestamp("reg_date"));				
			}
			
			System.out.println(id+" 정보 : "+mb);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		
		return mb;
	}
	// getMember(id)
	
	// updateMember(mb)
	public int updateMember(MemberBean mb){
		int check = -1;
		
		try {
			// 1,2
			con = getConnection();
			// 3 sql작성
		    sql ="select pass from model2_member where id=?";
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, mb.getId());
		    
		    // 4 실행
		    rs = pstmt.executeQuery();
		    
			// 5 rs 비교
		    if(rs.next()){
		    	if(mb.getPass().equals(rs.getString("pass"))){
		    		
		    		// 3 sql
		    		sql ="update model2_member set name=?,age=?,gender=?,email=? where id=?";
		    		pstmt = con.prepareStatement(sql);
		    		pstmt.setString(1, mb.getName());
		    		pstmt.setInt(2, mb.getAge());
		    		pstmt.setString(3, mb.getGender());
		    		pstmt.setString(4, mb.getEmail());
		    		pstmt.setString(5, mb.getId());
		    		
		    		// 4 실행
		    		pstmt.executeUpdate();		    		
		    		
		    		check=1;
		    	}else{
		    		check = 0; // 비밀번호 없음
		    	}
		    }else{
		    	check = -1;//아이디 없음
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		return check;
	}
	// updateMember(mb)
	
	// deleteMember(id,pass)
	public int deleteMember(String id,String pass){
		int check = -1;
		
		try {
			// 1,2
			con = getConnection();
			// 3 sql
			sql = "select pass from model2_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5 rs 비교 처리
			if(rs.next()){
				if(pass.equals(rs.getString("pass"))){
					// 3 sql
					sql="delete from model2_member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					
					// 4 실행
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
	// deleteMember(id,pass)
	
	// getMemberList()
	public List<MemberBean> getMemberList(){
		
		List<MemberBean> memberList = new ArrayList<MemberBean>();
		
		try {
			// 1,2
			con = getConnection();
			// 3 sql
			sql = "select * from model2_member";
			pstmt = con.prepareStatement(sql);
			
			// 4 실행
			rs = pstmt.executeQuery();
			
			// 5  rs 값 비교
		    while(rs.next()){
		    	MemberBean mb = new MemberBean();
		    	
		    	mb.setAge(rs.getInt("age"));
		    	mb.setEmail(rs.getString("email"));
		    	mb.setGender(rs.getString("gender"));
		    	mb.setId(rs.getString("id"));
		    	mb.setName(rs.getString("name"));
		    	mb.setPass(rs.getString("pass"));
		    	mb.setReg_date(rs.getTimestamp("reg_date"));
		    	
		    	//  한사람의 정보를  memberList배열에 한칸으로 저저장
		    	memberList.add(mb);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return memberList;
	}
	// getMemberList()
	
	
	
	
	
	
	
	

}

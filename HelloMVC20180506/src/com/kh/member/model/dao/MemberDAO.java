package com.kh.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.KeyStore.ProtectionParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.vo.Member;

import static com.kh.common.JDBCTemplate.*;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		//webinf-classes
		URL fileUrl = MemberDAO.class.getResource("/sql/member/member-query.properties");
		String fileName = fileUrl.getPath();
		System.out.println("fileName@MemberDAO="+fileName);
		
		
		try {
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int loginCheck(Connection conn, String userId, String password) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		
		String query = prop.getProperty("loginCheck");
		
		//1. 미완성쿼리를 가지고 객체생성
		
		try {
			pstmt = conn.prepareStatement(query);
			//2. 쿼리문 변수대입
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			pstmt.setString(3, userId);
			//3. 쿼리문 실행

			rset = pstmt.executeQuery();
			//4. 결과를 result에 담기
			if(rset.next()) {
				result = rset.getInt("login_check");
			}
			System.out.println("result@MemberDAO.loginCheck="+result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return result;
	}

	public Member selectOne(Connection conn, String userId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		//1 미완성 쿼리 객체생성
		
		try {
			//미완성쿼리객체생성
			pstmt = conn.prepareStatement(query);
			//쿼리완성
			pstmt.setString(1, userId);
			//쿼리실행
			rset = pstmt.executeQuery();
			//rset 데이터를 m에 대입
			if(rset.next()) {
				m = new Member();
				m.setUserId(rset.getString("userid"));
				m.setPassword(rset.getString("password"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public int insertMember(Connection conn, Member m) {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword() );
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result=0;
		PreparedStatement pstmt= null;
		
		String query = prop.getProperty("updateMember");
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, m.getPassword() );
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getGender());
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getHobby());
			pstmt.setString(8, m.getUserId());			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int MemberDelete(Connection conn, String userId) {
		int result =0; 
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember");
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberPwd(Connection conn, String userId, String pwd) {
		int result=0;
		PreparedStatement pstmt= null;
		
		String query = prop.getProperty("updateMemberPassword");
		try {
			pstmt = conn.prepareStatement(query);
			//pstmt.setString(1, m.getPassword() );
			pstmt.setString(1, pwd);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

 

}

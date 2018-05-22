package com.kh.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Subscriber;

import static com.kh.common.JDBCTemplate.*;

public class SubscriberDAO {
	
	private Properties prop = new Properties();
	
	public SubscriberDAO() {
		//webinf-classes
		URL fileUrl = SubscriberDAO.class.getResource("/sql/member/subscriber-query.properties");
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
	
	public int loginCheck(Connection conn, String sId, String sPassword) {
		int result = -1;
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		
		String query = prop.getProperty("loginCheck");
		
		//1. 미완성쿼리를 가지고 객체생성
		
		try {
			pstmt = conn.prepareStatement(query);
			//2. 쿼리문 변수대입
			pstmt.setString(1, sId);
			pstmt.setString(2, sPassword);
			pstmt.setString(3, sId);
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

	public Member selectOne(Connection conn, String sId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		//1 미완성 쿼리 객체생성
		
		try {
			//미완성쿼리객체생성
			pstmt = conn.prepareStatement(query);
			//쿼리완성
			pstmt.setString(1, sId);
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

	public int insertSubscriber(Connection conn, Subscriber s) {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertSubscriber");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, s.getsId());
			pstmt.setString(2, s.getsPassword());
			pstmt.setString(3, s.getsName());
			pstmt.setString(4, s.getsEmail());
			pstmt.setString(5, s.getsBirth());
			pstmt.setString(6, s.getsNumber());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateSubscriber(Connection conn, Subscriber s) {
		int result=0;
		PreparedStatement pstmt= null;
		
		String query = prop.getProperty("updateMember");
		try {
			pstmt = conn.prepareStatement(query);
 	
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int deleteSubscriber(Connection conn, String userId) {
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

 

}

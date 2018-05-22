package com.kh.admin.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import com.kh.member.model.vo.Member;


import static com.kh.common.JDBCTemplate.*;

public class AdminDAO {
	private Properties prop = new Properties();
	
	public AdminDAO() {
		URL fileUrl = AdminDAO.class.getResource("/sql/admin/admin-query.properties");
		String fileName = fileUrl.getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("RM :::::  ADMIN QUERY PROPERTIES ERROR");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public List<Member> selectMemberList(Connection conn) {
		
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = prop.getProperty("selectMemberList");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			System.out.println("RM ::::: list@AdminDAO" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectMemberByUserId(Connection conn, String searchKeyword) {
		List<Member> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByUserId");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			System.out.println("RM ::::: list@AdminDAO.selectByUserId" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Member> selectMemberByUserName(Connection conn, String searchKeyword) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByUserName");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			System.out.println("RM ::::: list@AdminDAO.selectByUserId" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public List<Member> selectMemberByGender(Connection conn, String searchKeyword) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByGender");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			System.out.println("RM ::::: list@AdminDAO.selectByUserId" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	public Member setMember(ResultSet rset) {
		Member m = new Member();
		try {
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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	public int selectMemberCount(Connection conn) {
		int totalMember = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			totalMember = rset.getInt("cnt");
			
			System.out.println("RM:::::DAO:::::totalMember@AdminDAO = " + totalMember);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		String query = prop.getProperty("selectMemberListByPaging");
		
		try {
			pstmt = conn.prepareStatement(query);
			//(공식2) 시작 rownum과 마지막 rownum을 구하는 공식
			//numPerPage = 5 인 경우
			//cPage = 1 -> 1, 5
			//cPage = 2 -> 6, 10
			//cPage = 3 -> 11, 15
			
			
			
			pstmt.setInt(1, numPerPage*(cPage-1) + 1);
			pstmt.setInt(2, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			System.out.println("RM ::::: list@AdminDAO" + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("RM:::::DAO:::::totalMember@AdminDAO = " + list);

		
		return list;
		
	}

	public int selectMemberCountByUserId(Connection conn, String searchKeyword) {
		int totalMember=0;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByUserId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			rset.next();
			totalMember = rset.getInt("cnt");
			
			System.out.println("RM:::::DAO:::::totalMember@AdminDAO = " + totalMember);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	public List<Member> selectMemberCountListByUserId(Connection conn, int cPage, int numPerPage, String searchKeyword) {
		List<Member> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountListByUserId");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  "%"+searchKeyword+"%");
			pstmt.setInt(2, numPerPage*(cPage-1) + 1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			
			System.out.println("RM:::::DAO:::::CountMemberUserId@AdminDAO = " + list);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int selectMemberCountByUserName(Connection conn, String searchKeyword) {
		int totalMember=0;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByUserName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			
			rset.next();
			totalMember = rset.getInt("cnt");
			System.out.println("----DAO----selectMemberCountByUserName" + totalMember);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	
	public List<Member> selectMemberCountListByUserName(Connection conn, int cPage, int numPerPage,
			String searchKeyword) {
		List<Member> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountListByUserName");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,  "%"+searchKeyword+"%");
			pstmt.setInt(2, numPerPage*(cPage-1) + 1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			
			System.out.println("RM:::::DAO:::::CountMemberUserId@AdminDAO = " + list);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int selectMemberCountByGender(Connection conn, String searchKeyword) {
		int totalMember=0;

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByGender");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			
			rset.next();
			totalMember = rset.getInt("cnt");
			System.out.println("----DAO----selectMemberCountByGender" + totalMember);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalMember;
	}

	public List<Member> selectMemberCountListByGender(Connection conn, int cPage, int numPerPage,
			String searchKeyword) {
		List<Member> list = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountListByGender");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, numPerPage*(cPage-1) + 1);
			pstmt.setInt(3, cPage*numPerPage);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Member m = new Member();
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
				list.add(m);
			}
			
			System.out.println("RM:::::DAO:::::CountMemberUserId@AdminDAO = " + list);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}



}

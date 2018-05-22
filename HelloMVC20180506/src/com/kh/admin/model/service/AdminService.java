package com.kh.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.model.dao.AdminDAO;
import com.kh.member.model.vo.Member;
import static com.kh.common.JDBCTemplate.*;

public class AdminService {

	public List<Member> selectMemberList() {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberList(conn);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByUserId(String searchKeyword) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByUserId(conn, searchKeyword);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByUserName(String searchKeyword) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByUserName(conn, searchKeyword);
		close(conn);
		return list;
		
	}

	public List<Member> selectMemberByGender(String searchKeyword) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberByGender(conn, searchKeyword);
		close(conn);
		return list;
		
	}

	public int selectMemberCount() {
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCount(conn);
		close(conn);
		return totalMember;
	}

	public List<Member> selectMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		
		List<Member> list = new AdminDAO().selectMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectMemberCountByUserId(String searchKeyword) {
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCountByUserId(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	public List<Member> selectMemberCountListByUserId(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberCountListByUserId(conn, cPage, numPerPage, searchKeyword);
		
		close(conn);
		return list;
	}

	public int selectMemberCountByUserName(String searchKeyword) {
		
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCountByUserName(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	
	public List<Member> selectMemberCountListByUserName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberCountListByUserName(conn, cPage, numPerPage, searchKeyword);
		
		close(conn);
		return list;
	}
	
	
	public int selectMemberCountByGender(String searchKeyword) {
		Connection conn = getConnection();
		int totalMember = new AdminDAO().selectMemberCountByGender(conn, searchKeyword);
		close(conn);
		return totalMember;
	}

	public List<Member> selectMemberCountListByGender(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDAO().selectMemberCountListByGender(conn, cPage, numPerPage, searchKeyword);
		
		close(conn);
		return list;
	}

}

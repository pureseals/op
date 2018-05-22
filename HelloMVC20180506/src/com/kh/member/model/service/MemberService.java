package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.vo.Member;

public class MemberService {
	//로그인처리를 위한 상수선언
	
	public static final int LOGIN_OK = 1;
	public static final int WRONG_PASSWORD = 0;
	public static final int ID_NOT_EXIST = -1;
	
	
	
	public int loginCheck(String userId, String password) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		
		int result = new MemberDAO().loginCheck(conn, userId, password);
		
		close(conn);
		return result;
	}



	public Member selectOne(String userId) {
		Connection conn = getConnection();
		Member m = new MemberDAO().selectOne(conn, userId);
		close(conn);
		return m;
	}



	public int insertMember(Member m) {
		Connection conn = getConnection();
		int result = new MemberDAO().insertMember(conn, m);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}



	public int updateMember(Member m) {
		
		Connection conn = getConnection();
		int result = new MemberDAO().updateMember(conn, m);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}



	public int MemberDelete(String userId) {
		Connection conn = getConnection();
		int result =0;
		
		result = new MemberDAO().MemberDelete(conn, userId);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		return result;
	}



	public int updateMemberPassword(String userId, String pwd) {
		Connection conn = getConnection();
		int result = new MemberDAO().updateMemberPwd(conn, userId, pwd);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

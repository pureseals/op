package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDAO;
import com.kh.member.model.dao.SubscriberDAO;
import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Subscriber;

public class SubscriberService {
	//로그인처리를 위한 상수선언
	
	public static final int LOGIN_OK = 1;
	public static final int WRONG_PASSWORD = 0;
	public static final int ID_NOT_EXIST = -1;
	
	
	
	public int loginCheck(String sId, String sPassword) {
		// TODO Auto-generated method stub
		Connection conn = getCinemaConnection();
		
		int result = new SubscriberDAO().loginCheck(conn, sId, sPassword);
		
		close(conn);
		return result;
	}



	public Member selectOne(String sId) {
		Connection conn = getCinemaConnection();
		Member m = new SubscriberDAO().selectOne(conn, sId);
		close(conn);
		return m;
	}



	public int insertSubscriber(Subscriber s) {
		Connection conn = getCinemaConnection();
		int result = new SubscriberDAO().insertSubscriber(conn, s);
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}



	public int updateSubscriber(Subscriber s) {
		
		Connection conn = getCinemaConnection();
		int result = new SubscriberDAO().updateSubscriber(conn, s);
		
		if(result>0) commit(conn);
		else rollback(conn);
		
		return result;
	}



	public int deleteSubscriber(String sId) {
		Connection conn = getCinemaConnection();
		int result =0;
		
		result = new SubscriberDAO().deleteSubscriber(conn, sId);
		
		if(result>0)commit(conn);
		else rollback(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

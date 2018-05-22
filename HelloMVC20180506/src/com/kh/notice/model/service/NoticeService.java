package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.notice.model.dao.NoticeDAO;
import com.kh.notice.model.vo.Notice;


public class NoticeService {

	public List<Notice> selectList() {
		Connection conn = getConnection();
		List<Notice> list = new NoticeDAO().selectList(conn);
		close(conn);
		return list;
	}

	public Notice selectOne(int noticeNo) {
		Connection conn = getConnection();
		Notice notice = new NoticeDAO().selectOne(conn, noticeNo);
		close(conn);
		return notice;
		
	}

	public int noticeWrite(Notice notice) {
		int result = 0;
		Connection conn = getConnection();
		result = new NoticeDAO().noticeWrite(conn, notice);
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public int updateNotice(Notice notice) {
		int result = 0;
		Connection conn = getConnection();
		result = new NoticeDAO().updateNotice(conn, notice);
		
		
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

	public int deleteNoticeStatus(int noticeNo) {
		int result = 0;
		Connection conn = getConnection();
		result = new NoticeDAO().deleteNoticeStatus(conn, noticeNo);
		
		
		
		//트랜잭션 처리
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

}

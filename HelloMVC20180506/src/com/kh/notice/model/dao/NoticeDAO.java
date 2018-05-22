package com.kh.notice.model.dao;

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
import static com.kh.common.JDBCTemplate.*;
import com.kh.notice.model.vo.Notice;

public class NoticeDAO {
	private Properties prop = new Properties();
	public NoticeDAO() {
		
		URL fileName = NoticeDAO.class.getResource("/sql/notice/notice-query.properties");
		String filePath = fileName.getPath();
		
		
		
		try {
			prop.load(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("----NoticeDAO ERROR ----notice-query.properties FileNotFoundException" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("----NoticeDAO ERROR ---- notice-query.properties IOException" + e.getMessage());
			e.printStackTrace();
		}
	
	}

	public List<Notice> selectList(Connection conn) {
		List<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("notice_no"));
				n.setNoticeTitle(rset.getString("notice_title"));
				n.setNoticeWriter(rset.getString("notice_writer"));
				n.setNoticeContent(rset.getString("notice_content"));
				n.setNoticeDate(rset.getDate("notice_date"));
				n.setFilePath(rset.getString("filepath"));
				n.setStatus(rset.getString("status"));
				list.add(n);
			}
			System.out.println("----NoticeDAO LIST VALUE ---- " + list );
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		return list;
	}

	public Notice selectOne(Connection conn, int noticeNo) {
		Notice notice = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("notice_no"));
				notice.setNoticeTitle(rset.getString("notice_title"));
				notice.setNoticeWriter(rset.getString("notice_writer"));
				notice.setNoticeContent(rset.getString("notice_content"));
				notice.setNoticeDate(rset.getDate("notice_date"));
				notice.setFilePath(rset.getString("filepath"));
				notice.setStatus(rset.getString("status"));
				
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int noticeWrite(Connection conn, Notice notice) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("noticeWrite");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeWriter());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setString(4, notice.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateNotice(Connection conn, Notice notice) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("noticeUpdate");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, notice.getNoticeTitle());
			pstmt.setString(2, notice.getNoticeWriter());
			pstmt.setString(3, notice.getNoticeContent());
			pstmt.setString(4, notice.getFilePath());
			pstmt.setInt(5, notice.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteNoticeStatus(Connection conn, int noticeNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("noticeDelete");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}

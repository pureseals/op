package com.kh.board.model.dao;


import static com.kh.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;


public class BoardDAO {
	Properties prop = new Properties();
	public BoardDAO() {
		String file = this.getClass().getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("---------PROPERTIES FILENOT FOUND EXCEPTION ----------");
		} catch (IOException e) {
			System.out.println("---------PROPERTIES IOEXCEPTION  ----------");
		}
	}

	public int showBoardCount(Connection conn) {
		int count =0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = prop.getProperty("showBoardCount");
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("CNT");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		return count;
	}

	public List<Board> showBoardListPart(Connection conn, int bPage, int numPerPage) {
		List<Board> boardList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = prop.getProperty("showBoardListPart");	
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, numPerPage*(bPage-1) + 1);
			pstmt.setInt(2, bPage*numPerPage);
			
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board b = new Board();
				b.setBoard_no(rs.getInt("BOARD_NO"));
				b.setBoard_title(rs.getString("BOARD_TITLE"));
				b.setBoard_writer(rs.getString("BOARD_WRITER"));
				b.setBoard_content(rs.getString("BOARD_CONTENT"));
				b.setBoard_original_filename(rs.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoard_renamed_filename(rs.getString("BOARD_RENAMED_FILENAME"));
				b.setBoard_date(rs.getDate("BOARD_DATE"));
				b.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
				boardList.add(b);
			};
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}

	public int insertBoard(Connection conn, Board b) {
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b.getBoard_title());
			pstmt.setString(2, b.getBoard_writer());
			pstmt.setString(3, b.getBoard_content());
			pstmt.setString(4, b.getBoard_original_filename());
			pstmt.setString(5, b.getBoard_renamed_filename());
			//쿼리실행
			result = pstmt.executeUpdate();
			System.out.println("result@boardDAO="+result);
			
		} catch (SQLException e) {
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Board selectOne(Connection conn, int boardNo) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");
		
		
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Board();
				
				b.setBoard_no(rset.getInt("BOARD_NO"));
				b.setBoard_title(rset.getString("BOARD_TITLE"));
				b.setBoard_writer(rset.getString("BOARD_WRITER"));
				b.setBoard_content(rset.getString("BOARD_CONTENT"));
				b.setBoard_original_filename(rset.getString("BOARD_ORIGINAL_FILENAME"));
				b.setBoard_renamed_filename(rset.getString("BOARD_RENAMED_FILENAME"));
				b.setBoard_date(rset.getDate("BOARD_DATE"));
				b.setBoard_readcount(rset.getInt("BOARD_READCOUNT"));
			}
			
			System.out.println("board@boardDAO ="+b);
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			
		} finally {
			
			close(rset);
			close(pstmt);
		
		}
		
		
		
		return b;
	}

	public int increaseReadCount(Connection conn, int boardNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseReadCount");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  boardNo);
			
			
			result = pstmt.executeUpdate();
			System.out.println("result@BoardDAO = " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			
		}
		
		
		
		
		
		return result;
	}

	public int getBoardNumber(Connection conn, String boardWriter) {
		int boardNumber =0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("getBoardNumber");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardWriter);
			rset = pstmt.executeQuery();
			
			
			rset.next();
			
			
			boardNumber = rset.getInt("board_no");
			
			System.out.println("보드넘버 DAO " +  boardNumber);
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return boardNumber;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insertBoardComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			pstmt.setString(5, bc.getBoardCommentRef() ==0? null : String.valueOf(bc.getBoardCommentRef()));
			
			result = pstmt.executeUpdate();
			System.out.println("result@BoardCommentDAO= " + result);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<BoardComment> selectCommentList(Connection conn, int boardNo) {

		List<BoardComment> commentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCommentList");
		

		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				
				bc.setBoardCommentNo(rset.getInt("BOARD_COMMENT_NO"));
				bc.setBoardCommentLevel(rset.getInt("BOARD_COMMENT_LEVEL"));
				bc.setBoardCommentWriter(rset.getString("BOARD_COMMENT_WRITER"));
				bc.setBoardCommentContent(rset.getString("BOARD_COMMENT_CONTENT"));
				bc.setBoardRef(rset.getInt("BOARD_REF"));
				
				//null값은 자동으로 0으로 치환되서 대입됨.
				bc.setBoardCommentRef(rset.getInt("BOARD_COMMENT_REF"));
				bc.setBoardCommentDate(rset.getDate("BOARD_COMMENT_DATE"));
				commentList.add(bc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		
		
		return commentList;
	}
 

	public int getBordCommentCounte(Connection conn, int board_no) {
		int count=0;
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String query = prop.getProperty("countComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, board_no);
			
			
			rset = pstmt.executeQuery();
			
			
			rset.next();
			
			count = rset.getInt("CNT");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
			
		}
		
		
		return count;
	}

	public int deleteComment(Connection conn, int commentNo) {
		
		int result =0; 
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("deleteComment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			
			result = pstmt.executeUpdate();
			System.out.println("ㄱflkadjfkdsfjkdjafksd "+ result );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
 
}

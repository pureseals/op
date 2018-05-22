package com.kh.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {

	public int showBoardCount() {
		Connection conn = getConnection();
		int count = new BoardDAO().showBoardCount(conn);
		close(conn);
		return count;
	}

	public List<Board> showBoardListPart(int bPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> boardList = new BoardDAO().showBoardListPart(conn, bPage, numPerPage);
		
		close(conn);
		return boardList;
	}

	public int insertBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoard(conn, b);
		
		if(result > 0) {
			commit(conn); 
		}else {
			rollback(conn);
		}		
		
		close(conn);
		return result;
	}

	public Board selectOne(int boardNo) {
		Connection conn = getConnection();
		Board b = new BoardDAO().selectOne(conn, boardNo);
		
		close(conn);
		
		return b;
	}

	public void increaseReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDAO().increaseReadCount(conn, boardNo);
		if(result>0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
	}

	public int getBoardNumber(String boardWriter) {
		Connection conn = getConnection();
		int boardNumber = new BoardDAO().getBoardNumber(conn, boardWriter);
		
		close(conn);
		return boardNumber;
		
		
		
	}

	public int insertBoardComment(BoardComment bc) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoardComment(conn, bc);
		close(conn);
		return result;
	}

	public List<BoardComment> selectCommentList(int boardNo) {
		Connection conn = getConnection();
		List<BoardComment> commentList = new BoardDAO().selectCommentList(conn, boardNo);
		close(conn);
		return commentList;
	}


	public Integer getBordCommentCounte(int board_no) {
		Connection conn = getConnection();
		int count = new BoardDAO().getBordCommentCounte(conn, board_no);
		close(conn);
		return count;
	}

	public int deleteComment(int commentNo) {
		Connection conn = getConnection();
		int result= new BoardDAO().deleteComment(conn, commentNo);
		
		
		if(result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return result;
	}



}

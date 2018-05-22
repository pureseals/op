package com.kh.board.model.vo;

import java.util.Date;

public class Board {
	private int board_no ;
	private String board_title ;
	private String board_writer;
	private String board_content  ;
	private String board_original_filename ;
	private String board_renamed_filename  ;
	private Date board_date  ;
	private int board_readcount;
	public Board() {
	}
	public Board(int board_no, String board_title, String board_writer, String board_content,
			String board_original_filename, String board_renamed_filename, Date board_date, int board_readcount) {
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
		this.board_original_filename = board_original_filename;
		this.board_renamed_filename = board_renamed_filename;
		this.board_date = board_date;
		this.board_readcount = board_readcount;
	}
	
	//Board 등록용 생성자
	
	public Board(String board_title, String board_writer, String board_content,
			String board_original_filename, String board_renamed_filename) {
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
		this.board_original_filename = board_original_filename;
		this.board_renamed_filename = board_renamed_filename;
	}
	
	
	
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_original_filename() {
		return board_original_filename;
	}
	public void setBoard_original_filename(String board_original_filename) {
		this.board_original_filename = board_original_filename;
	}
	public String getBoard_renamed_filename() {
		return board_renamed_filename;
	}
	public void setBoard_renamed_filename(String board_renamed_filename) {
		this.board_renamed_filename = board_renamed_filename;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public int getBoard_readcount() {
		return board_readcount;
	}
	public void setBoard_readcount(int board_readcount) {
		this.board_readcount = board_readcount;
	}
	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", board_title=" + board_title + ", board_writer=" + board_writer
				+ ", board_content=" + board_content + ", board_original_filename=" + board_original_filename
				+ ", board_renamed_filename=" + board_renamed_filename + ", board_date=" + board_date
				+ ", board_readcount=" + board_readcount + "]";
	}
}
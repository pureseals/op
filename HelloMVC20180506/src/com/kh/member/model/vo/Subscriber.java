package com.kh.member.model.vo;

import java.util.Date;

public class Subscriber {
	
	int sNo;
	String sId;
	String sPassword;
	String sName;
	String sEmail;
	String sBirth;
	String sNumber;
	Date eDate;
	public Subscriber() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Subscriber(int sNo, String sId, String sPassword, String sName, String sEmail, String sBirth, String sNumber,
			Date eDate) {
		super();
		this.sNo = sNo;
		this.sId = sId;
		this.sPassword = sPassword;
		this.sName = sName;
		this.sEmail = sEmail;
		this.sBirth = sBirth;
		this.sNumber = sNumber;
		this.eDate = eDate;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public String getsId() {
		return sId;
	}
	public void setsId(String sId) {
		this.sId = sId;
	}
	public String getsPassword() {
		return sPassword;
	}
	public void setsPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsEmail() {
		return sEmail;
	}
	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}
	public String getsBirth() {
		return sBirth;
	}
	public void setsBirth(String sBirth) {
		this.sBirth = sBirth;
	}
	public String getsNumber() {
		return sNumber;
	}
	public void setsNumber(String sNumber) {
		this.sNumber = sNumber;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	@Override
	public String toString() {
		return "Subscriber [sNo=" + sNo + ", sId=" + sId + ", sPassword=" + sPassword + ", sName=" + sName + ", sEmail="
				+ sEmail + ", sBirth=" + sBirth + ", sNumber=" + sNumber + ", eDate=" + eDate + "]";
	}
	
}

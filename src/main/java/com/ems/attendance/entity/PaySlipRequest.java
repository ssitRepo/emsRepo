package com.ems.attendance.entity;

public class PaySlipRequest {

	private int employeeID;
	private String password;
	private int reqEmployeeID;
	private String month;
	private int year;
	private String type;
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getReqEmployeeID() {
		return reqEmployeeID;
	}
	public void setReqEmployeeID(int reqEmployeeID) {
		this.reqEmployeeID = reqEmployeeID;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

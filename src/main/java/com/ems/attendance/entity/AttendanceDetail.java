 package com.ems.attendance.entity;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="attendancedetail")

public class AttendanceDetail{
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long taskid;
		
		private int employeeId;
		private int mgrID1;
		
		private Date tdate;
		private int client;
		private String projectHours;
		private int training;
		private int teamMeeting;
		private int travel;
		private int others;
		private int holiday;
		private int leaveHrs;
		private int totalHours;
		private int approvedBy;
		private Date approvedDate;
		private Date submittedDate;
		private String comments;
		private String status;
		private String clientname;
		public String getClientname() {
			return clientname;
		}
		public void setClientname(String clientname) {
			this.clientname = clientname;
		}
		public String getProjectHours() {
			return projectHours;
		}
		public void setProjectHours(String projectHours) {
			this.projectHours = projectHours;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Date getTdate() {
			
			return tdate;
		}
		public void setTdate(Date tdate) {
			System.out.println("date is"+tdate);
			
			this.tdate = tdate;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
	
		public int getClient() {
			return client;
		}
		public void setClient(int client) {
			this.client = client;
		}
		public int getHoliday() {
			return holiday;
		}
		public void setHoliday(int holiday) {
			this.holiday = holiday;
		}
	
		public int getLeaveHrs() {
			return leaveHrs;
		}
		public void setLeaveHrs(int leaveHrs) {
			this.leaveHrs = leaveHrs;
		}
		public int getTotalHours() {
			return totalHours;
		}
		public void setTotalHours(int totalHours) {
			this.totalHours = totalHours;
		}
		
		public Long getTaskid() {
			return taskid;
		}
		public void setTaskid(Long taskid) {
			this.taskid = taskid;
		}
		public int getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(int employeeId) {
			this.employeeId = employeeId;
		}
		public int getMgrID1() {
			return mgrID1;
		}
		public void setMgrID1(int mgrID1) {
			this.mgrID1 = mgrID1;
		}
		
		public int getTraining() {
			return training;
		}
		public void setTraining(int training) {
			this.training = training;
		}
		public int getTeamMeeting() {
			return teamMeeting;
		}
		public void setTeamMeeting(int teamMeeting) {
			this.teamMeeting = teamMeeting;
		}
		public int getTravel() {
			return travel;
		}
		public void setTravel(int travel) {
			this.travel = travel;
		}
		public int getOthers() {
			return others;
		}
		public void setOthers(int other) {
			this.others = other;
		}
		public int getApprovedBy() {
			return approvedBy;
		}
		public void setApprovedBy(int approvedBy) {
			this.approvedBy = approvedBy;
		}
		public Date getApprovedDate() {
			return approvedDate;
		}
		public void setApprovedDate(Date approvedDate) {
			this.approvedDate = approvedDate;
		}
		public Date getSubmittedDate() {
			return new Date();
		}
		public void setSubmittedDate(Date submittedDate) {
			this.submittedDate = submittedDate;
		}
		
		
		
				
}
package com.ems.attendance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="employeedetail")
public class EmployeeDetail {
		
	
		@Id
		@Column(name = "id")
		long id;
		
		@Column(name = "employee_id")
		long employeeId;
		
		@Column(name = "designation")
		String designation;
		
		@Column(name = "serviceLine")
		String serviceLine;
		
		@Column(name = "client")
		String client;
		
		@Column(name = "dob")
		String dob;		
		
		@Column(name = "gender")
		String gender;
		
		@Column(name = "bloodGroup")
		String bloodGroup;
		
		@Column(name = "phoneNo")
		long phoneNo;
		
		@Column(name = "address")
		String address;
		
			
		@Column(name = "mailID")
		String mailID;
		
		@Column(name = "firstName")
		String firstName;
		
		@Column(name = "lastName")
		String lastName;
		
		@Column(name = "middleName")
		String middleName;
		
		@Column(name = "title")
		String title;
		
		@Column(name = "password")
		String password;
		
		@Column(name = "confirmPassword")
		String confirmPassword;
		
		@Column(name = "doj")
		String doj;
		
		@Column(name = "mgrID1")
		int mgrID1;
		
		@Column(name = "mgrID2")
		int mgrID2;
		
		@Column(name = "defaultMgrID")
		int defaultMgrID;
		
		@Column(name = "totalLeaves")
		int totalLeaves;
		
		@Column(name = "pendingLeaves")
		String pendingLeaves;

		@Column(name = "utilisedLeaves")
		String utilisedLeaves;
		
		@Column(name = "panNumber")
		String panNumber;
		
		@Column(name = "status")
		String status;
		
		@Column(name = "role")
		String role;
		
		@Column(name = "adUser")
		String adUser;
		@Column(name = "lastLoginDate")
		private Date lastLoginDate;
		
		@Column(name = "isLoggedIn")
		private int isLoggedIn;
		
		public int getIsLoggedIn() {
			return isLoggedIn;
		}

		public void setIsLoggedIn(int isLoggedIn) {
			this.isLoggedIn = isLoggedIn;
		}

		public Date getLastLoginDate() {
			return lastLoginDate;
		}

		public void setLastLoginDate(Date lastLoginDate) {
			this.lastLoginDate = lastLoginDate;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getServiceLine() {
			return serviceLine;
		}

		public void setServiceLine(String serviceLine) {
			this.serviceLine = serviceLine;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getAdUser() {
			return adUser;
		}

		public void setAdUser(String adUser) {
			this.adUser = adUser;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getConfirmPassword() {
			return confirmPassword;
		}

		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}

		/*@OneToOne(fetch=FetchType.LAZY, mappedBy="eDetails")
	    private UserAccount user;
	
		

		public UserAccount getUser() {
			return user;
		}

		public void setUser(UserAccount user) {
			this.user = user;
		}
*/
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public long getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(long phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getDoj() {
			return doj;
		}

		public void setDoj(String doj) {
			this.doj = doj;
		}


		public long getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(long employeeId) {
			this.employeeId = employeeId;
		}

		public String getDob() {
			return dob;
		}

		public void setDob(String dob) {
			this.dob = dob;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBloodGroup() {
			return bloodGroup;
		}

		public void setBloodGroup(String bloodGroup) {
			this.bloodGroup = bloodGroup;
		}

		

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}


		public String getMailID() {
			return mailID;
		}

		public void setMailID(String mailId) {
			this.mailID = mailId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getDateOfJoining() {
			return doj;
		}

		public void setDateOfJoining(String dateOfJoining) {
			this.doj = dateOfJoining;
		}

		public int getMgrID1() {
			return mgrID1;
		}

		public void setMgrID1(int mgrID1) {
			this.mgrID1 = mgrID1;
		}

		public int getMgrID2() {
			return mgrID2;
		}

		public void setMgrID2(int mgrID2) {
			this.mgrID2 = mgrID2;
		}

		public int getDefaultMgrID() {
			return defaultMgrID;
		}

		public void setDefaultMgrID(int defaultmgrID) {
			this.defaultMgrID = defaultmgrID;
		}

		public int getTotalLeaves() {
			return totalLeaves;
		}

		public void setTotalLeaves(int totalLeaves) {
			this.totalLeaves = totalLeaves;
		}

		public String getPendingLeaves() {
			return pendingLeaves;
		}

		public void setPendingLeaves(String pendingLeaves) {
			this.pendingLeaves = pendingLeaves;
		}

		public String getUtilisedLeaves() {
			return utilisedLeaves;
		}

		public void setUtilisedLeaves(String utilisedLeaves) {
			this.utilisedLeaves = utilisedLeaves;
		}


}

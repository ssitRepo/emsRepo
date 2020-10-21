
package com.ems.attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity(name = "employeedetail")
@Table(name = "employeedetail")
public class UserAccount {

	@Id
	@Column(name = "role")
	@NotEmpty(message = "*Please provide your id")
    String	role;

	@Column(name = "password")
	@NotEmpty(message = "*Please provide your PASSWORD")
    String password;    
    
	@Column(name = "employee_id")
	@NotEmpty(message = "*Please provide your EMPLOYEE ID")
    long employeeId;

	@Column(name = "adUser")
	@NotEmpty(message = "*Please provide your EMPLOYEE ID")
    String adUser;
/*	@OneToOne()
    @JoinColumn(name="employeeId")
    private EmployeeDetail eDetails;
	
	public EmployeeDetail geteDetails() {
		return eDetails;
	}

	public void seteDetails(EmployeeDetail eDetails) {
		this.eDetails = eDetails;
	}*/

	

    public String getAdUser() {
		return adUser;
	}

	public void setAdUser(String adUser) {
		this.adUser = adUser;
	}

	public String getPassword() {
        return password;
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
        this.password = password;
    }

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}


}
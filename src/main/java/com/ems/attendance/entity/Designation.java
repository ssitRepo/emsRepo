package com.ems.attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="designation")
public class Designation {
	@Id
	@Column(name = "degID")
private int degID;
	@Column(name = "designationName")
private String designationName;
public int getDegID() {
	return degID;
}
public void setDegID(int degID) {
	this.degID = degID;
}
public String getDesignationName() {
	return designationName;
}
public void setDesignationName(String designationName) {
	this.designationName = designationName;
}
}
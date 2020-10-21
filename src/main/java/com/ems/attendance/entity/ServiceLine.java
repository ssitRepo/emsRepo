package com.ems.attendance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="serviceline")
public class ServiceLine {
	@Id
	@Column(name = "serID")
private int serID;
	@Column(name = "servicelineName")
private String servicelineName;
	public int getSerID() {
		return serID;
	}
	public void setSerID(int serID) {
		this.serID = serID;
	}
	public String getServicelineName() {
		return servicelineName;
	}
	public void setServicelineName(String servicelineName) {
		this.servicelineName = servicelineName;
	}

}
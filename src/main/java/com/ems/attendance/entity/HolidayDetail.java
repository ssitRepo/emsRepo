package com.ems.attendance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="holidaydetail")
public class HolidayDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
  	@Column(name = "hdate")
	Date hdate;
  	
  	@Column(name = "country")
	String country;
  	
	@Column(name = "reason")
	String reason;
	
	@Column(name = "day")
	String day;
	
	public Date getHdate() {
		return hdate;
	}

	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Long getId() {
		return id; 
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
}

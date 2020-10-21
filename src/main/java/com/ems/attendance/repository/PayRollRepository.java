package com.ems.attendance.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ems.attendance.entity.PayRoll;

@Repository
public interface PayRollRepository extends CrudRepository<PayRoll, Long>{
	
	 @Query("Select P from payroll P where P.Employee_ID=?1 and P.PayPeriodMonth=?2 and P.PayPeriodYear=?3")
	 PayRoll findByMonthYear(long empid,String month,int year);
	
	}
	
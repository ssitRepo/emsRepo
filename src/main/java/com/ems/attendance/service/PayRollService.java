package com.ems.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.Client;
import com.ems.attendance.entity.Designation;
import com.ems.attendance.entity.LeaveDetail;
import com.ems.attendance.entity.PayRoll;
import com.ems.attendance.entity.ServiceLine;
import com.ems.attendance.repository.ClientRepository;
import com.ems.attendance.repository.DesignationRepository;
import com.ems.attendance.repository.PayRollRepository;
import com.ems.attendance.repository.ServiceLineRepository;

@Service("PayRollService")
public class PayRollService {
	
	@Autowired
	public PayRollRepository payRollRepository;
	
	public PayRoll findByMonthYear(long empid, String month, int year) {
		PayRoll payroll = payRollRepository.findByMonthYear(empid, month, year);
        if (payroll != null) {
            return payroll;
        }
        return null;
    }
	}

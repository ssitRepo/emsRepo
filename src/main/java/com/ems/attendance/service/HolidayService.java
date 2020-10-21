package com.ems.attendance.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.HolidayDetail;
import com.ems.attendance.repository.HolidayRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service("holidayservice")
public class HolidayService {
	
	
	HolidayRepository holidayrepository;
	
	@Autowired
	 public HolidayService(HolidayRepository holidayrepository) {
		this.holidayrepository = holidayrepository;
		
	}

	public String addHoliday(HolidayDetail holidaydetail) {
		HolidayDetail holidaydet = null;// (HolidayDetail)holidayrepository.findByDate(holidaydetail.getDate());
		if(holidaydet==null) {
		 System.out.println("HELLO"+holidaydetail.getHdate()+"    "+holidaydetail.getCountry());
	        holidayrepository.save(holidaydetail);
	        return "Holiday has been added on : " + holidaydetail.getHdate();
	        
	    }
		return "DATA ALREADY THERE";
	}
	 
	  public List<HolidayDetail> findAllHoliday() {
	    	List<HolidayDetail> holidaylist = (List<HolidayDetail>) holidayrepository.findAll();
	    	List<HolidayDetail> l=holidayrepository.findBycountry("India");
	    	//List<HolidayDetail> findByCountry();
	    	
	        if (holidaylist != null) {
	           return holidaylist;
	        }
	        return null;
	    }
	  public List<HolidayDetail> findCounryHolidays(String country) {
	    	List<HolidayDetail> holidaylist=holidayrepository.findBycountry(country);
	    	//List<HolidayDetail> findByCountry();
	        if (holidaylist != null) {
	        	System.out.println("p---india=======>"+holidaylist);
	        	holidaylist.stream().forEach(a->System.out.println("holiday:"+a.getReason()));
	           return holidaylist;
	        }
	        return null;
	    }
	 	

	    public HolidayDetail findByDate(Date date) {
	    	HolidayDetail holidaylist = (HolidayDetail) holidayrepository.findByHdate(date);

	        if (holidaylist != null) {
	            return holidaylist;
	        }
	        return null;
	    }
	 	

	    public String deleteByid(long id) {
	    	//holidayrepository.deleteByDate(date);
	    	holidayrepository.deleteById(id);
	        return "Deleted Successfully";
	    }
	 
}

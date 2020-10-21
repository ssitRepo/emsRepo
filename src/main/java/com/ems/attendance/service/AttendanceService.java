package com.ems.attendance.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.AttendanceDetail;
import com.ems.attendance.entity.Client;
import com.ems.attendance.entity.EmployeeDetail;
import com.ems.attendance.entity.LeaveDetail;
import com.ems.attendance.repository.AttendanceRepository;
import com.ems.attendance.repository.EmployeeRepository;

@Service("attendanceservice")
public class AttendanceService {

	private AttendanceRepository attendancerepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	ConfigService configService;
	
	@Autowired
	MailService mailService;
	@Autowired
	public AttendanceService(AttendanceRepository attendancerepository) {
		super();
		this.attendancerepository = attendancerepository;
	}
public String addAttendence(List<AttendanceDetail> attendancedetailList) throws IOException {
	System.out.println("-------->"+attendancedetailList);
	EmployeeDetail empdetail;
	 EmployeeDetail mgrdetail;
	 int empid=0;
	 int mgrid=0;
	 String status=null;
	List<AttendanceDetail> attendancedetailList1= new ArrayList<>();
	
	for(AttendanceDetail l:attendancedetailList){
		if(l.getTotalHours()>0){
			attendancedetailList1.add(l)	;
		}
		empid=l.getEmployeeId();
		mgrid=l.getMgrID1();
		System.out.println("===========status==>"+status);
		if(status==null)
			status=l.getStatus();
	}
	
	attendancerepository.saveAll(attendancedetailList1);
	if(empid>0 && mgrid>0){
			empdetail = employeeRepository.findByEmployeeId(empid);
			mgrdetail=employeeRepository.findByMgrId(mgrid);
			System.out.println("=======**********====status==>"+status);
			mailService.sendMailTimesheet(1, mgrdetail, empdetail);
			/*if(status!=null && (status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Rejected")))
				mailService.sendstatusTimesheet(1, mgrdetail, empdetail);
			else
				mailService.sendMailTimesheet(1, mgrdetail, empdetail);*/
	}
	        return "User addAttendence has been added, Employee ID = ";
     

    }


public String updateAttendence(List<AttendanceDetail> attendancedetailList) throws IOException {
	System.out.println("-----update--->"+attendancedetailList);
	EmployeeDetail empdetail;
	 EmployeeDetail mgrdetail;
	 int empid=0;
	 int mgrid=0;
	 String status=null;
	 List<Long> ids=new ArrayList<>();
	 String taskids="";
	 int approvedby=0;
	 Date approveddate= new Date();
	List<AttendanceDetail> attendancedetailList1= new ArrayList<>();
	
	for(AttendanceDetail l:attendancedetailList){
		if(l.getTotalHours()>0 && !l.getStatus().trim().equalsIgnoreCase("submitted")){
			//attendancedetailList1.add(l)	;
			taskids=taskids+","+l.getTaskid();
			ids.add(l.getTaskid());
			approvedby=l.getApprovedBy();
			if(status==null)
				status=l.getStatus();
		}
		empid=l.getEmployeeId();
		mgrid=l.getMgrID1();
		System.out.println("====upd=======status==>"+status);
		
	}
	taskids=taskids.substring(1);
	//attendancerepository.saveAll(attendancedetailList1);
	System.out.println(taskids+"===tASKS========status==>"+status);
	attendancerepository.updateTimeSheets(status, ids,approvedby,approveddate);
	if(empid>0 && mgrid>0){
			empdetail = employeeRepository.findByEmployeeId(empid);
			mgrdetail=employeeRepository.findByMgrId(mgrid);
			System.out.println("=======**********====status==>"+status);
			mailService.sendstatusTimesheet(1, mgrdetail, empdetail,status);
			/*if(status!=null && (status.equalsIgnoreCase("Approved") || status.equalsIgnoreCase("Rejected")))
				mailService.sendstatusTimesheet(1, mgrdetail, empdetail);
			else
				mailService.sendMailTimesheet(1, mgrdetail, empdetail);*/
	}
	        return "User addAttendence has been added, Employee ID = ";
     

    }

public List<AttendanceDetail> findbetweenDates(int empid,Date fromdate,Date todate) {
    List<AttendanceDetail> attendanceList = (List<AttendanceDetail>) attendancerepository.findbetweenDates(empid,fromdate,todate);

    if (attendanceList != null) {
    	return attendanceList;
    }
    return null;
}
public List<AttendanceDetail> findAllByEmpID(int mgrID1,int employeeid){
    List<AttendanceDetail> attendanceList = (List<AttendanceDetail>) attendancerepository.findAllByEmpID(mgrID1, employeeid);
    List<AttendanceDetail> finaattendanceList = new ArrayList<>();
	int count=0;
    if (attendanceList != null) {
    	for(AttendanceDetail l:attendanceList){
       		int clientID=l.getClient();
       		String cname=null;
       		
       		Client c = configService.getClient(clientID);
       		if(c!=null)
       		{
       			cname=c.getClientName();
       			if(cname!=null){
       				cname = cname.toUpperCase() ;
       			}
       		}
       		System.out.println("cname--"+cname);
       		l.setClientname(cname);
       		
       		finaattendanceList.add(l);
       		count++;
       	}
    	return finaattendanceList;
    }
    return null;
}
	 
/*
	
	private AttendanceRepository attendancerepository;
	
	@Autowired
	public AttendanceService(AttendanceRepository attendancerepository) {
		super();
		this.attendancerepository = attendancerepository;
	}

	public String addUser(AttendanceDetail attendancedetail) {
    	
    		attendancerepository.save(attendancedetail);
	        return "User account has been added, Employee ID = " + attendancedetail.getEmployeeId();
	     

	    }
	public String addAttendence(List<AttendanceDetail> attendancedetailList) {
    	
		attendancerepository.saveAll(attendancedetailList);
        return "User addAttendence has been added, Employee ID = ";
     

    }
	 
	    public List<AttendanceDetail> findAllUser() {
	        List<AttendanceDetail> attendanceList = (List<AttendanceDetail>) attendancerepository.findAll();

	        if (attendanceList != null) {
	        	return attendanceList;
	        }
	        return null;
	    }

	    public AttendanceDetail[] findByempid(String employeeid) {
		AttendanceDetail[] attendanceList =  attendancerepository.findByEmployeeId(employeeid);

	        if (attendanceList != null) {
	            return attendanceList;
	        }
	        return null;
	  }
	    
	    
	    public long countByEmployeeIdAndDepartmentId(String employeeid,String departmentid) {
			long attendanceList =  attendancerepository.countByEmployeeIdAndDepartmentId(employeeid,departmentid);
		            return attendanceList;
		        
		  }
	    
	    public long countByDepartmentIdAndMonthAndAvailable(String departmentid,String month,Boolean available) {
			long attendanceList =  attendancerepository.countByDepartmentIdAndMonthAndAvailable(departmentid,month,available);
		            return attendanceList;
		        
		  }
	    public long countByDepartmentIdAndMonth(String departmentid,String month) {
			long attendanceList =  attendancerepository.countByDepartmentIdAndMonth(departmentid,month);
		            return attendanceList;
		        
		  }
	    public long countByEmployeeIdAndDepartmentIdAndMonth(String employeeid,String departmentid,String month) {
			long attendanceList =  attendancerepository.countByEmployeeIdAndDepartmentIdAndMonth(employeeid,departmentid,month);
		            return attendanceList;
		        
		  }
	    
	    public long countByEmployeeIdAndDepartmentIdAndAvailable(String employeeid,String departmentid,Boolean available) {
			long attendanceList =  attendancerepository.countByEmployeeIdAndDepartmentIdAndAvailable(employeeid,departmentid,available);
		            return attendanceList;
		        
		  }
	    
	    public AttendanceDetail findBydate(String month) {
			AttendanceDetail attendanceList = (AttendanceDetail) attendancerepository.findBydate(month);

		        if (attendanceList != null) {
		            return attendanceList;
		        }
		        return null;
		  }
	    
	    public AttendanceDetail[] findBydeptid(String departmentid) {
		   AttendanceDetail[] attendanceList = (AttendanceDetail[]) attendancerepository.findByDepartmentId(departmentid);
	        if (attendanceList != null) {
	        		return attendanceList;
	        }
	        return null;
	  }

	    public AttendanceDetail findByName(String employeeid,String departmentid) {
		 AttendanceDetail attendanceList = (AttendanceDetail) attendancerepository.findByEmployeeIdAndDepartmentId(employeeid, departmentid);

	        if (attendanceList != null) {
	           return attendanceList;
	        }

	        return null;
	  }

	    
	    
	    
	    
	    

	    public Object[] sortdeptview(String departmentid,String month,String shift) {
//						AttendanceDetail[] attendanceList = (AttendanceDetail[]) 
//								attendancerepository.findByDepartmentId(departmentid);
//		
//			for(AttendanceDetail atten : attendanceList) {
//				atten.setAttencount(countByDepartmentIdAndMonth(departmentid,month));
//			}
			return attendancerepository.findByAttencountOrderByAttencountAsc(departmentid,month,shift);
	    
	    }
	    
	    
	    
	    
	    
	    
	    
	    public String updateUser(String employeeid,Boolean available,String date) {
	    	AttendanceDetail attendanceList = (AttendanceDetail) attendancerepository.findByEmployeeIdAndDate(employeeid,date);
	    	if (attendanceList != null) {
	        	
	       // 	attendanceList.setAvailable(available);
	        	attendancerepository.save(attendanceList);
	        	return "User updated Successfully";
	        }
	        return "User update Failed";
	    }


	    public String deleteByEmpid(String employeeid) {
	    	attendancerepository.deleteByEmployeeId(employeeid);
	        return "Record deleted successfully";
	    }

	    public String deleteBydeptid(String departmentid) {
	    	attendancerepository.deleteByDepartmentId(departmentid);
	       return "Record deleted successfully";
	    }
	   
	    
	    public String deleteByUserNameAndPassword(String employeeid,String departmentid) {
	    	attendancerepository.deleteByEmployeeIdAndDepartmentId(employeeid,departmentid);
	    	return "Record deleted successfully";
	    }
*/}

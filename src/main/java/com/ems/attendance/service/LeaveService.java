package com.ems.attendance.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.EmployeeDetail;
import com.ems.attendance.entity.LeaveDetail;
import com.ems.attendance.repository.EmployeeRepository;
import com.ems.attendance.repository.LeaveRepository;

@Service("LeaveService")
public class LeaveService {

	@Autowired
	MailService mailService;
	@Autowired 
	EmployeeService employeeservice;
    
    private LeaveRepository leaveRepository;
    private EmployeeRepository employeeRepository;
	
	@Autowired
    public LeaveService(LeaveRepository leaveRepository,EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;   
        this.employeeRepository=employeeRepository;
    }

   
    public String addLeave(LeaveDetail leavedet) throws IOException {
    	 EmployeeDetail empdetail;
    	 EmployeeDetail mgrdetail;
    	 LeaveDetail l=leaveRepository.save(leavedet);
    	
    	empdetail = employeeRepository.findByEmployeeId(leavedet.getEmployeeId());
    	if(empdetail!=null){
    		mgrdetail=employeeRepository.findByMgrId((empdetail.getMgrID1()));
    		mailService.sendMailLeave(1, mgrdetail,empdetail,l.getId());
    	}
    		return "Leave has been added, user name = " + leavedet.getEmployeeId();
    	
       
    }
    
    public List<LeaveDetail> findAllUser() {
        List<LeaveDetail> leaveList = (List<LeaveDetail>) leaveRepository.findAll();
        if (leaveList != null) {
            return leaveList;
        }
        return null;
    }

    public String updateLeave(int leaveId,String approveddate,String status,int mgrid ,long empid) throws IOException {
    	 EmployeeDetail empdetail;
    	 EmployeeDetail mgrdetail;
    	LeaveDetail l = leaveRepository.findById(leaveId);
    	if(l!=null) {
    		
    		l.setStatus(status);
    		l.setApprovedBy(mgrid);
    		l.setApprovedDate(approveddate);
    		leaveRepository.save(l);
    		mgrdetail=employeeRepository.findByMgrId(mgrid);
    		empdetail = employeeRepository.findByEmployeeId(empid);
    		System.out.println("before empservice updagte leaves empid:"+empid);
    		mailService.sendstatusLeaveMail(1, mgrdetail,empdetail,l.getId(),l.getStatus());
    		if(status.equalsIgnoreCase("ACCEPTED"))
    			employeeRepository.updateLeaves(empid);
		
    	}
		return "Leave has been update, user name = " ;
	
   
}
    
    public LeaveDetail[] findByEmployeeId(int employeeId) {
    	LeaveDetail[] leaveList=null;
    	LeaveDetail[] finalleaveList = {};
    	int count=0;
       try{ 
    	   leaveList = leaveRepository.findByEmployeeId(employeeId);
    	   finalleaveList = new LeaveDetail[leaveList.length];
    	//   finalleaveList.length=leaveList.length;   
       }
       catch(Exception e){
    	   e.printStackTrace();
       }
        if (leaveList != null) {
        	for(LeaveDetail l:leaveList){
        		int empid=l.getEmployeeId();
        		String fname=null;
        		String mname=null;
        		String finalname=null;
        		EmployeeDetail e = employeeservice.findByempid(empid);
        		System.out.println("e-------"+e);
        		if(e!=null)
        		{
        			fname=e.getFirstName();
        			if(fname!=null){
        			fname = fname.toLowerCase().substring(0, 1).toUpperCase() + fname.toLowerCase().substring(1);
        			}
        			
        			mname=e.getMiddleName();
        			if(mname!=null)
        			{
        				mname = mname.toLowerCase().substring(0, 1).toUpperCase() + mname.toLowerCase().substring(1);
        			}
        			finalname= fname+" "+mname; 
        		}
        		System.out.println("finalname--"+finalname);
        		l.setEmpname(finalname);
        		
        		finalleaveList[count]=l;
        		count++;
        	}
           return finalleaveList;
        }
        return null;
    }
    public LeaveDetail[] findByemployeeId(int employeeId) {
    	LeaveDetail[] leaveList=null;
    	LeaveDetail[] finalleaveList = null ;
    	int count=0;
       try{ leaveList = leaveRepository.findByEmployeeId1(employeeId);
       finalleaveList = new LeaveDetail[leaveList.length];
       }
       catch(Exception e){
    	   e.printStackTrace();
       }
       if (leaveList != null) {
       	for(LeaveDetail l:leaveList){
       		int empid=l.getEmployeeId();
       		String fname=null;
       		String mname=null;
       		String finalname=null;
       		EmployeeDetail e = employeeservice.findByempid(empid);
       		System.out.println("e-------"+e);
       		if(e!=null)
       		{
       			fname=e.getFirstName();
       			if(fname!=null){
       			fname = fname.toLowerCase().substring(0, 1).toUpperCase() + fname.toLowerCase().substring(1);
       			}
       			
       			mname=e.getMiddleName();
       			if(mname!=null)
       			{
       				mname = mname.toLowerCase().substring(0, 1).toUpperCase() + mname.toLowerCase().substring(1);
       			}
       			finalname= fname+" "+mname; 
       		}
       		System.out.println("finalname--"+finalname);
       		l.setEmpname(finalname);
       		
       		finalleaveList[count]=l;
       		count++;
       	}
          return finalleaveList;
       }
        return null;
    }

    
    public String deleteByEmpid(String employeeid) {
    	leaveRepository.deleteByEmployeeId(employeeid);
        return "Leave data has been deleted successfully.";

    }
   
    public String cancelleave(int id) {
    	leaveRepository.cancel(id);
        return "Leave data has been  cancelled successfully.";
    }
    
}

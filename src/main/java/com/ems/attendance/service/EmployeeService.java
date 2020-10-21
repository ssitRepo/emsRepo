package com.ems.attendance.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.EmployeeDetail;
import com.ems.attendance.repository.EmployeeRepository;

@Service("EmployeeService")
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeerepository;
	
	@Autowired
	MailService mailService;
	@Autowired
	public static Environment env;

	 public String addUser(EmployeeDetail employeedetail) throws IOException {
		 EmployeeDetail mgrdetail;
	        if(employeedetail!=null) {
	        	System.out.println("env in service---"+env);
	        	
		 	employeerepository.save(employeedetail);
		 	
		 	mgrdetail = employeerepository.findByMgrId(employeedetail.getMgrID1());
		 	if(employeedetail.getId()>0)
		    mailService.sendMailUser(2,employeedetail);
		 	else
		 		mailService.sendMailUser(1,employeedetail);
		 	//mailService.sendMail2(employeedetail);
	        return "User account has been added";
	        }
	        return null;
	    }
	 
	    public List<EmployeeDetail> findAllUser() {
	    	List<EmployeeDetail> employeeList = (List<EmployeeDetail>) employeerepository.findAll();

	        if (employeeList != null) {
	        	return employeeList;
	        }
	        return null;
	    }
	    public List<EmployeeDetail> findAllReportees(int mgrID) {
	    	List<EmployeeDetail> employeeList = (List<EmployeeDetail>) employeerepository.findReportees(mgrID);

	        if (employeeList != null) {
	        	return employeeList;
	        	
	        	
	        }
	        return null;
	    }
	    
	 /*   public String findByempdept(String employeeid) {
			 EmployeeDetail employee = (EmployeeDetail) employeerepository.findByEmployeeId(employeeid);

			  if (employee != null) {
		           return employee.getDepartment();
		        }

		        return null;
		  }
	    */
	    /*public EmployeeDetail[] findBydeptemp(String department) {
			 EmployeeDetail[] employee = null;//(EmployeeDetail[]) employeerepository.findByDepartment(department);

			  if (employee != null) {
		           return employee;
		        }

		        return null;
		  }*/
	    
	    public EmployeeDetail findByempid(long employeeid) {
		 EmployeeDetail employee =  (EmployeeDetail) employeerepository.findByEmployeeId(employeeid);

		  if (employee != null) {
			//  MailService.sendMail2();
	           return employee;
	        }

	        return null;
	    }
	    public EmployeeDetail findByMgrID(long employeeid) {
			 EmployeeDetail employee =  (EmployeeDetail) employeerepository.findByMgrId(employeeid);

			  if (employee != null) {
				//  MailService.sendMail2();
		           return employee;
		        }

		        return null;
		    }
	    public EmployeeDetail findByadUSer(String adUSer) {
			 EmployeeDetail employee =  (EmployeeDetail) employeerepository.findByadUser(adUSer);

			  if (employee != null) {
		           return employee;
		        }

		        return null;
		    }



	    public String deleteByEmpid(String employeeid) {
	    	employeerepository.deleteByEmployeeId(employeeid);
	        return "User data has been deleted successfully.";

	    }
	    public String updateLeaves(int employeeid) {
	    	employeerepository.updateLeaves(employeeid);
	        return "User data has been updated successfully.";

	    }
	    public String updateLastLogin(int employeeid) {
	    	employeerepository.updateLastLogin(employeeid, new Date());
	        return "User data has been updated successfully.";

	    }
	    public String updateLogout(int employeeid) {
	    	employeerepository.updateLogout(employeeid);
	        return "User data has been updated successfully.";

	    }
	    public String updatePassword(long employeeId,String pwd,String confirmpwd) throws IOException{
	    	employeerepository.updatePassword(employeeId,pwd,confirmpwd);
	    	EmployeeDetail e=employeerepository.findByEmployeeId(employeeId);
	    	mailService.sendpwdResetMail(e.getMailID(), e.getFirstName());
	        return "User password has been updated successfully.";

	    }
	    
}

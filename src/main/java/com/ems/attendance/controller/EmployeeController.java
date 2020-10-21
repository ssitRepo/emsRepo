package com.ems.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ems.attendance.entity.EmployeeDetail;
import com.ems.attendance.entity.User;
import com.ems.attendance.service.EmployeeService;

@Controller
@RequestMapping(path="/employeedetail")
public class EmployeeController {
	
	@Autowired 
	EmployeeService employeeservice;
	
	@PostMapping(path="/addemployee")
	@CrossOrigin
	@ResponseBody
	 public ResponseEntity<?> addUser(@RequestBody EmployeeDetail employeedetail) {
		System.out.println("add employee");
		HttpHeaders headers = new HttpHeaders();
		long id=0;
        
        try {   	
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.addUser(employeedetail));
    }catch (Exception e) {
    	headers.add("Message", "false");
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
		 	 
	    }
	 
	@GetMapping(path="/findalluseremployee")
	@CrossOrigin
	@ResponseBody
	    public ResponseEntity<?> findAllUser() {

		HttpHeaders headers = new HttpHeaders();
        
        try {   	
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.findAllUser());
    }catch (Exception e) {
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
	    }
	
	@GetMapping(path="/findallReportees")
	@CrossOrigin
	@ResponseBody
	    public ResponseEntity<?> findallReportees(@RequestParam int mgrID) {

		HttpHeaders headers = new HttpHeaders();
        
        try {   	
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.findAllReportees(mgrID));
    }catch (Exception e) {
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
	    }	
	
	

	@GetMapping(path="/findbyempidemployee")
	@CrossOrigin
	@ResponseBody
	    public ResponseEntity<?> findByempid(@RequestParam long employeeid) {
HttpHeaders headers = new HttpHeaders();
        
        try {   	
        	//System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.findByempid(employeeid));
    }catch (Exception e) {
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to get the user");
	}
		
	  }
	
	@GetMapping(path="/findByaduser")
	@CrossOrigin
	@ResponseBody
public ResponseEntity<?> findByaduser(@RequestParam String aduser) {
HttpHeaders headers = new HttpHeaders();
  try {   	
        	System.out.println("en da ipdi "+aduser);
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.findByadUSer(aduser));
    }catch (Exception e) {
    	headers.add("Message", "false");
    	e.printStackTrace();
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to get the user");
	}
		
}

	
	//To get dept name using emp  name
	@GetMapping(path="/findbyempdept")
	@CrossOrigin
	@ResponseBody
	    public ResponseEntity<?> findByempdept(@RequestParam String employeeid) {
HttpHeaders headers = new HttpHeaders();
        
        try {   	
        	//System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
    		//return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.findByempdept(employeeid));
        	return null;
    }catch (Exception e) {
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
		
	  }
	
	/*@GetMapping(path="/findbydeptemp")
	@CrossOrigin
	@ResponseBody
	    public ResponseEntity<?> findBydeptemp(@RequestParam String department) {
HttpHeaders headers = new HttpHeaders();
        
        try {   	
        	//System.out.println("en da ipdi "+employeeid+employeeservice.findByempid(employeeid));
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.findBydeptemp(department));
    }catch (Exception e) {
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
		
	  }
*/
	@GetMapping(path="/deletebyempidemployee")
	@CrossOrigin
	@ResponseBody
public ResponseEntity<?> deleteByEmpid(@RequestParam String employeeid) {
	HttpHeaders headers = new HttpHeaders();
	try {   	
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.deleteByEmpid(employeeid));
		    }catch (Exception e) {
		    	headers.add("Message", "false");
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}

}
	
	@GetMapping(path="/logout")
	@CrossOrigin
	@ResponseBody
public ResponseEntity<?> logout(@RequestParam int employeeid) {
	HttpHeaders headers = new HttpHeaders();
	try {   	
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.updateLogout(employeeid));
		    }catch (Exception e) {
		    	headers.add("Message", "false");
	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}

}
	@PostMapping(path = "/updatepwd")
    @CrossOrigin()
    @ResponseBody
    public ResponseEntity<?> Updatepwd(@RequestBody User u) {
    	HttpHeaders headers = new HttpHeaders();
        try {
        	
        	System.out.println("--userAccountService"+u.getEmployeeId());
        	if(u!=null && u.getEmployeeId()<=0){
        		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Invalid EmployeeID");	
        	}else{
        	return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(employeeservice.updatePassword(u.getEmployeeId(),u.getPassword(),u.getConfirmpassword()));
        	}
    }catch (Exception e) {
		// TODO: handle exception
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to update the password");
	}
    }
}

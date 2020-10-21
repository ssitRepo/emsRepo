package com.ems.attendance.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.attendance.entity.LeaveDetail;
import com.ems.attendance.service.LeaveService;

@RestController
@RequestMapping(path = "/leavedetail")
public class LeaveController {

    @Autowired
    LeaveService leaveservice;

    @PostMapping(path = "/adduserleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> adduserleave(@RequestBody LeaveDetail leavedetail) {
    	HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    	System.out.println("---"+leavedetail);
	    	leavedetail.setStatus("Pending Approval");	
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.addLeave(leavedetail));
	    }catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
	    	System.out.println("--leave add-"+e);
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
        
    }

    
    @GetMapping(path = "/findalluserleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> findAllUser() {
HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.findAllUser());
	    }catch (Exception e) {
			// TODO: handle exception
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
        
    }

    
    @GetMapping(path = "/findbyempidleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> findByEmployeeId(@RequestParam int employeeId) {
HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.findByEmployeeId(employeeId));
	    }catch (Exception e) {
			// TODO: handle exception
	    	
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
       
    }

    @GetMapping(path = "/findbyMgridleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> findbyMgridleave(@RequestParam int mgrID) {
HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.findByemployeeId(mgrID));
	    }catch (Exception e) {
			// TODO: handle exception
	    	headers.add("Message", "false");
	    	e.printStackTrace();
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
       
    }
    @GetMapping(path = "/findbydeptidleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> findByDepartmentId(@RequestParam String deptid) {
HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    
	    		return null;//ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.findByDepartmentId(deptid));
	    }catch (Exception e) {
			// TODO: handle exception
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
       
    }
    
    @GetMapping(path = "/updateleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateLeave(@RequestParam int leaveId,@RequestParam String approveddate,@RequestParam String status,@RequestParam int mgrid,long empid) {
HttpHeaders headers = new HttpHeaders();
    	
	    try {  
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.updateLeave(leaveId,approveddate,status,mgrid,empid));
	    }catch (Exception e) {
	    	e.printStackTrace();
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to update leave status");
		}
       
    }
    
    
    @GetMapping(path = "/cancelleave")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> cancelleave(@RequestParam int leaveid) {
HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(leaveservice.cancelleave(leaveid));
	    }catch (Exception e) {
			// TODO: handle exception
	    	headers.add("Message", "false");
	    	e.printStackTrace();
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
    }
    
}

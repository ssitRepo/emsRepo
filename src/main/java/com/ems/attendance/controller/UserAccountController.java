
package com.ems.attendance.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
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

import com.ems.attendance.entity.User;
import com.ems.attendance.entity.UserAccount;
import com.ems.attendance.service.UserAccountService;

@RestController
@RequestMapping(path = "/userAccount")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;


    @PostMapping(path = "/checkuser")
    @CrossOrigin()
    @ResponseBody
    public ResponseEntity<?> Checkuser(HttpServletRequest req,HttpServletResponse res,@RequestBody User u) {
    	HttpHeaders headers = new HttpHeaders();
        
        try {
        	/*Enumeration e=req.getParameterNames();
        	System.out.println("--out loop"+e);	
        	while(e.hasMoreElements()){
        		System.out.println("--loop"+e.nextElement());	
        	}*/
        	System.out.println("--userAccountService"+u.getEmployeeId());
        	return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(userAccountService.CheckUser(u.getEmployeeId(),u.getPassword()));
    }catch (Exception e) {
		// TODO: handle exception
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to check the user");
	}
    }
    @GetMapping(path = "/checkuser1")
    @CrossOrigin()
    @ResponseBody
    public ResponseEntity<?> Checkuser1(@RequestParam int employeeId,@RequestParam String password) {
    	HttpHeaders headers = new HttpHeaders();
        
        try {
        	
        	System.out.println("--userAccountService"+userAccountService);
        	return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(userAccountService.CheckUser(employeeId,password));
    }catch (Exception e) {
		// TODO: handle exception
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
    }
    
    
    
    
    @PostMapping(path = "/adduseraccount")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> addUser(@RequestBody UserAccount useraccount) {
    	HttpHeaders headers = new HttpHeaders();
    	
    	    try {
    	    		userAccountService.addUser(useraccount);
    	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(useraccount);
    	    }catch (Exception e) {
				// TODO: handle exception
    	    	headers.add("Message", "false");
    	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
			}
    }

    @GetMapping(path = "/findalluseraccounts")
    @CrossOrigin    
    @ResponseBody
    public ResponseEntity<?> findAllUser() {
    	HttpHeaders headers = new HttpHeaders();
    	
	    try {
	    		
	    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(userAccountService.findAllUser());
	    }catch (Exception e) {
			// TODO: handle exception
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
		}
       
    }


    @GetMapping(path = "/findbyempid")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> findByEmployeeId(@RequestParam long employeeId) {
    	HttpHeaders headers = new HttpHeaders();
        
        try {
        	
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(userAccountService.findByEmployeeId(employeeId));
    }catch (Exception e) {
		// TODO: handle exception
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
    }

    @PostMapping(path = "/updateuser")
    @CrossOrigin
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody UserAccount useraccount) {
HttpHeaders headers = new HttpHeaders();
        
        try {
        	
    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(userAccountService.updateUser(useraccount));
    }catch (Exception e) {
		// TODO: handle exception
    	headers.add("Message", "false");
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
	}
    	
    }
    
}
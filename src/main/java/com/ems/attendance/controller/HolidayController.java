package com.ems.attendance.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.attendance.entity.HolidayDetail;
import com.ems.attendance.service.HolidayService;

@RestController
@RequestMapping(path="/holidaydetail")
public class HolidayController {
	@Autowired
	HolidayService holidayservice;
	
	 @PostMapping(path = "/addholiday")
	 @CrossOrigin
	 @ResponseBody
	 public ResponseEntity<String> addUser(@RequestBody HolidayDetail holidaydetail) {
		 HttpHeaders headers = new HttpHeaders();
	    	
		    try {
		    
		    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(holidayservice.addHoliday(holidaydetail));
		    }catch (Exception e) {
				// TODO: handle exception
		    	headers.add("Message", "false");
		    	e.printStackTrace();
		    	System.out.println(e.getMessage());
		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the holiday");
			}
		
	    }
	 
	 
	 @GetMapping(path = "/findallholiday")
	 @CrossOrigin
	    @ResponseBody
	    public ResponseEntity<?> findAllHoliday() {
		 HttpHeaders headers = new HttpHeaders();
	    	
		    try {
		    
		    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(holidayservice.findAllHoliday());
		    }catch (Exception e) {
				// TODO: handle exception
		    	headers.add("Message", "false");
		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to get holiday list");
			}
	    }
	 	
	 @GetMapping(path = "/findallCountryholidays")
	 @CrossOrigin
	    @ResponseBody
	    public ResponseEntity<?> findallCountryholidays(@RequestParam String country) {
		 HttpHeaders headers = new HttpHeaders();
	    	
		    try {
		    
		    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(holidayservice.findCounryHolidays(country));
		    }catch (Exception e) {
				// TODO: handle exception
		    	headers.add("Message", "false");
		    	e.printStackTrace();
		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to get holiday list");
			}
	    }
	 	
	 @GetMapping(path = "/findbydateholiday")
	 @CrossOrigin("*")
	    @ResponseBody
	    public ResponseEntity<?> findByDate(@RequestParam Date date) {
		 HttpHeaders headers = new HttpHeaders();
	    	
		    try {
		    
		    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(holidayservice.findByDate(date));
		    }catch (Exception e) {
				// TODO: handle exception
		    	headers.add("Message", "false");
		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
			}
	        
	    }
	 	
	 @GetMapping(path = "/deletebyId")
	 @CrossOrigin("*")
	    @ResponseBody
	    public ResponseEntity<?> deletebyId(@RequestParam long id) {
		 HttpHeaders headers = new HttpHeaders();
	    	
		    try {
		    
		    		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(holidayservice.deleteByid(id));
		    }catch (Exception e) {
				// TODO: handle exception
		    	headers.add("Message", "false");
		    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add the user");
			}
	        
		 		
	    }
	 
}

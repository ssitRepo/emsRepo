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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ems.attendance.entity.Client;
import com.ems.attendance.entity.Designation;
import com.ems.attendance.entity.ServiceLine;
import com.ems.attendance.service.AttendanceService;
import com.ems.attendance.service.ConfigService;

@Controller
@RequestMapping(path="/configuration")
public class ConfigurationController {


	@Autowired
	ConfigService configService;
	
//	@RequestMapping(value="/savedesignation",method=RequestMethod.POST) 
	@PostMapping(path="/savedesignation")
	@CrossOrigin
	@ResponseBody
	public void saveDesignation(@RequestBody Designation designation){
		System.out.println("savedesignation............."+designation);
		configService.saveDesignation(designation);
	}
	@GetMapping(path="/allDesignations")
	@CrossOrigin
	@ResponseBody
	public List<Designation> getAllDesignations(){
		System.out.println("allDesignations.............");
		return configService.getAllDesignation();
	}
	
	
	@PostMapping(path="/saveserviceline")
	@CrossOrigin
	@ResponseBody
	public void saveserviceline(@RequestParam String servicelineName){
		
		ServiceLine serviceLine = new ServiceLine();
		serviceLine.setServicelineName(servicelineName);
		configService.saveServiceLine(serviceLine);
	}
	@GetMapping(path="/allServiceLines")
	@CrossOrigin
	@ResponseBody
	public List<ServiceLine> getAllServiceLines(){
		System.out.println("getAllServiceLines.............");
		return configService.getAllServiceLines();
	}
	
	@GetMapping(path="/ServiceLine")
	@CrossOrigin
	@ResponseBody
	public ServiceLine getServiceLine(int serID){
		System.out.println("getServiceLine.............");
		return configService.getServiceLine(serID);
	}
	
	@PostMapping(path="/saveclient")
	@CrossOrigin
	@ResponseBody
	public ResponseEntity<?> saveclient(@RequestParam String clientName){
		HttpHeaders headers = new HttpHeaders();
		try {
			System.out.println("saveclient............."+clientName);
			Client client = new Client();
			client.setClientName(clientName);
			configService.saveClient(client);
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(configService.saveClient(client));
		} catch (Exception e) {
	    	headers.add("Message", "false");
	    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to add client");
		}
	}
	@GetMapping(path="/allClients")
	@CrossOrigin
	@ResponseBody
	public List<Client> getAllClients(){
		System.out.println("getAllClients.............");
		return configService.getAllClients();
	}
	@GetMapping(path="/allempClients")
	@CrossOrigin
	@ResponseBody
	public List<Client> getAllempClients(@RequestParam long empID){
		System.out.println("getAllClients.............");
		return configService.getAllempClients(empID);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET) 
	public String getEms(){
		System.out.println("first page-------------------.............");
		//return new ModelAndView("NewFile");
		return "index";

	}
}
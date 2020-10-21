package com.ems.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.Client;
import com.ems.attendance.entity.Designation;
import com.ems.attendance.entity.ServiceLine;
import com.ems.attendance.repository.ClientRepository;
import com.ems.attendance.repository.DesignationRepository;
import com.ems.attendance.repository.ServiceLineRepository;

@Service("ConfigService")
public class ConfigService {
	@Autowired
	public DesignationRepository designationRepository;
	@Autowired
	public ServiceLineRepository serviceLineRepository;
	@Autowired
	public ClientRepository clientRepository;
	
	
	public void saveDesignation(Designation designation){
		 designationRepository.save(designation);
	}
	public List<Designation> getAllDesignation(){
		List<Designation> l= (List<Designation>)designationRepository.findAll();
		return l;
	}
	
	public void saveServiceLine(ServiceLine serviceLine){
		serviceLineRepository.save(serviceLine);
	}
	public List<ServiceLine> getAllServiceLines(){
		List<ServiceLine> l= (List<ServiceLine>)serviceLineRepository.findAll();
		return l;
	}
	
	public ServiceLine getServiceLine(int serID){
		ServiceLine l= (ServiceLine)serviceLineRepository.findByserID(serID);
		return l;
	}
	public Client getClient(int clientID){
		Client l= (Client)clientRepository.findByclientID(clientID);
		return l;
	}

	public Client saveClient(Client client){
		return clientRepository.save(client);
	}
	public List<Client> getAllClients(){
		List<Client> l= (List<Client>)clientRepository.findAll();
		return l;
	}
	public List<Client> getAllempClients(long empid){
		List<Client> l= (List<Client>)clientRepository.findByclientbyempID(empid);
		return l;
	}
}



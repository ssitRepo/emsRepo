package com.ems.attendance.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.Client;
import com.ems.attendance.entity.DepartmentDetail;
import com.ems.attendance.entity.Designation;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

	
	Client findByclientID(int clientID);
   @Query("select c from clients c,employeedetail e where e.client=c.clientID and e.employeeId=?1")
	List<Client> findByclientbyempID(long empID);
   //Designation[] getAllDesignation();
   
   @Transactional
   void deleteByclientID(int clientID);	

}	
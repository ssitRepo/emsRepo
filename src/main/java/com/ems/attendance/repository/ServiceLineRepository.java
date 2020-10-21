package com.ems.attendance.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.DepartmentDetail;
import com.ems.attendance.entity.Designation;
import com.ems.attendance.entity.ServiceLine;

@Repository
public interface ServiceLineRepository extends CrudRepository<ServiceLine, Integer> {

	
	ServiceLine findByserID(int serID);
   
   //Designation[] getAllDesignation();
   
   @Transactional
   void deleteByserID(int serID);	

}	
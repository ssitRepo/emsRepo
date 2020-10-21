package com.ems.attendance.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.DepartmentDetail;
import com.ems.attendance.entity.Designation;

@Repository
public interface DesignationRepository extends CrudRepository<Designation, Integer> {

	
   DepartmentDetail findBydegID(int degID);
   
   //Designation[] getAllDesignation();
   
   @Transactional
   void deleteBydegID(int degID);	

}	
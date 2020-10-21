package com.ems.attendance.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.DepartmentDetail;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentDetail, Long> {

	
   DepartmentDetail findByDepartmentId(String departmentid);
   
   @Transactional
   void deleteByDepartmentId(String departmentId);	

}	
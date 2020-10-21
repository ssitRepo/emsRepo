package com.ems.attendance.repository;


import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.UserAccount;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

   
    
    UserAccount findByEmployeeId(long employeeId);
    UserAccount findByAdUser(String adUser);
    
 //   @Query("select u.password from UserAccount u inner join u.eDetails eD where eD.employee_id = :employeeId")
 	//	UserAccount findByEmployeeId(@Param("employeeId") String employeeId);
 
    @Transactional
    void deleteByEmployeeId(String employeeId);

}	
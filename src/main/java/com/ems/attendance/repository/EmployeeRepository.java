package com.ems.attendance.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.EmployeeDetail;
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeDetail, Long> {
   
	@Query("SELECT E  FROM employeedetail E WHERE employeeId=?1 AND STATUS='Active'")
    EmployeeDetail findByEmployeeId(long l);
    
	@Query("SELECT E  FROM employeedetail E WHERE employeeId=?1")
    EmployeeDetail findByMgrId(long l);
	
	@Query("SELECT E  FROM employeedetail E WHERE adUser=?1 AND STATUS='Active'")
    EmployeeDetail findByadUser(String adUser);
    //EmployeeDetail findByEmployeeIdAndShift(String employeeid,String shift);
    
	@Query("select e1 from employeedetail e1 where e1.mgrID1=?1")
    List<EmployeeDetail> findReportees(int mgrID);
    
    @Modifying
    @Transactional
    @Query("Update employeedetail e set e.pendingLeaves=(e.pendingLeaves-1) , e.utilisedLeaves=(e.utilisedLeaves+1) where e.employeeId=?1")
    void updateLeaves(long employeeId);

    @Modifying
    @Transactional
    @Query("Update employeedetail e set e.lastLoginDate=?2 ,e.isLoggedIn=1 where e.employeeId=?1")
    void updateLastLogin(long employeeId,Date d);
    
    @Modifying
    @Transactional
    @Query("Update employeedetail e set e.password=?2 ,e.confirmPassword=?3 where e.employeeId=?1")
    void updatePassword(long employeeId,String pwd,String confirmpwd);
    
    @Modifying
    @Transactional
    @Query("Update employeedetail e set e.isLoggedIn=0 where e.employeeId=?1")
    void updateLogout(long employeeId);


    
    @Transactional
    void deleteByEmployeeId(String employeeId);

}
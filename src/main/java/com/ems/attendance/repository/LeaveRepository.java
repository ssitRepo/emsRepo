package com.ems.attendance.repository;



import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.LeaveDetail;


@Repository
public interface LeaveRepository extends CrudRepository<LeaveDetail, Long> {

	//@Query("select l from leavedetail l where l.employeeId=?1  AND (UPPER(l.status)='Pending Approval') or l.status=''")
    LeaveDetail[] findByEmployeeId(int employeeId);

    LeaveDetail findByEmployeeIdAndFromdate(int employeeId,String fromdate);
    LeaveDetail findById(int Id);
    
   // LeaveDetail[] findByDeptId(String deptId);
    @Query("select l from leavedetail l where l.employeeId in (select e1.employeeId from employeedetail e1 where e1.mgrID1=?1) AND (UPPER(l.status)='Pending Approval') or l.status=''")
    LeaveDetail[] findByEmployeeId1(int mgrID1);
    
    @Transactional
    void deleteByEmployeeId(String employeeId);
    
    @Modifying
    @Transactional
    @Query("Update leavedetail l set l.status='CANCELLED' where l.id=?1")
    void cancel(int id);

}	
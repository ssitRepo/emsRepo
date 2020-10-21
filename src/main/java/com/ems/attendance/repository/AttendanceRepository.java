package com.ems.attendance.repository;


import java.util.Date;
import java.util.List;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.AttendanceDetail;

@Repository
public interface AttendanceRepository extends CrudRepository<AttendanceDetail, Long> {
	
	@Query("Select e from attendancedetail e where e.employeeId=?1 and e.tdate between ?2 and ?3")
	List<AttendanceDetail>  findbetweenDates(int employeeid, Date fromdate,Date todate);
	
	@Query("Select e from attendancedetail e where e.mgrID1=?1 and e.employeeId=?2 and upper(e.status)='SUBMITTED' order by taskid")
	List<AttendanceDetail>  findAllByEmpID(int mgrID1,int employeeid);

	
	@Modifying
    @Transactional
    @Query("Update attendancedetail a set a.status=?1 , a.approvedBy=?3 , approvedDate=?4 where a.taskid in(?2)")
    void updateTimeSheets(String status,List taskids,int approvedBy,Date approvedDate);

	/*AttendanceDetail[] findByEmployeeId(String employeeid);
	
	 
	AttendanceDetail[] findByDepartmentId(String departmentid);
	
	AttendanceDetail findBydate(String month);
	
	AttendanceDetail findByEmployeeIdAndDepartmentId(String employeeid,String departmentid);
	
	
	Long countByEmployeeIdAndDepartmentId(String employeeid,String departmentid);
	
	Long countByEmployeeIdAndDepartmentIdAndAvailable(String employeeid,String departmentid,Boolean available);
	
	Long countByEmployeeIdAndDepartmentIdAndMonth(String employeeid,String departmentid,String month);
	
	@Query(value = "SELECT count(*) as total FROM attendancedetail where department_id = ?1 and month = ?2 group by employee_id")
	Long countByDepartmentIdAndMonth(String departmentid,String month);

	Long countByDepartmentIdAndMonthAndAvailable(String departmentid,String month,Boolean available);

	@Transactional
	void deleteByEmployeeIdAndDepartmentId(String employeeid,String departmentid);
	
	@Transactional
	void deleteByDepartmentId(String departmentid);
	
	@Transactional
	void deleteByEmployeeId(String employeeid);

	AttendanceDetail findByEmployeeIdAndDate(String employeeid, String date);

	@Query(value = "SELECT employeeId,count(available) as total FROM attendancedetail where available=1 and department_id = ?1 and month = ?2 and shift = ?3 group by employee_id order by count(available) desc")
	Object[] findByAttencountOrderByAttencountAsc(String departmentid,String month,String shift);
*/
}	
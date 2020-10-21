
package com.ems.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.attendance.entity.EmployeeDetail;
import com.ems.attendance.entity.UserAccount;
import com.ems.attendance.repository.UserAccountRepository;

@Service("userAccountService")
@ComponentScan("BCryptPasswordEncoder")
public class UserAccountService {

	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired 
	EmployeeService employeeservice;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private UserAccountRepository userAccountRepository;
	
	@Autowired
    public UserAccountService(UserAccountRepository userAccountRepository) {//,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userAccountRepository = userAccountRepository; 
       // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

   
    public String addUser(UserAccount useraccount) {
    	String ret = null;
    	useraccount.setPassword(bCryptPasswordEncoder.encode(useraccount.getPassword()));
    	UserAccount userAccountList = userAccountRepository.findByEmployeeId(useraccount.getEmployeeId());
    	if(userAccountList==null) {
    		userAccountRepository.save(useraccount);
    		ret = "User account has been added, user name = " + useraccount.getEmployeeId();
    	}
        return ret;

    }
    
    
    
    public long CheckUser(int employeeId,String password) {
        EmployeeDetail userAccountList=null;
		try {
			//userAccountList = userAccountRepository.findByAdUser(employeeId);
			userAccountList = employeeservice.findByempid(employeeId);
			//userAccountList = employeeservice.findByadUSer(employeeId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//UserAccount userAccountList = userAccountRepository.findByEmployeeId(employeeId);
        System.out.println("userAccountList::"+userAccountList);
   //     System.out.println("userAccountList status: "+userAccountList.geteDetails().getStatus());
        //  System.out.println(bCryptPasswordEncoder.matches(password,encryptPass));
        if(userAccountList!=null){
        	String encryptPass = bCryptPasswordEncoder.encode(userAccountList.getPassword());
            System.out.println("encrypt PASSWORD : "+encryptPass+"===>"+password);
            System.out.println(bCryptPasswordEncoder.matches(password,encryptPass));
            System.out.println(userAccountList.getRole());
          
        	if(bCryptPasswordEncoder.matches(password, encryptPass)) {
        		//employeeservice.findByempid(employeeId)
        		  System.out.println(userAccountList.getRole());
        		/*  if(userAccountList.getIsLoggedIn()==1){
        			  //Already logged in
        			  return 5;
        		  }else
        		  {
        			  employeeservice.updateLastLogin(employeeId);
        		  }*/
        		  employeeservice.updateLastLogin(employeeId);
        		  if(userAccountList.getRole().trim().toLowerCase().equals("admin"))
        			  return 1;
        		  else if(userAccountList.getRole().trim().toLowerCase().equals("manager"))
        			  return 2;
        		  else if(userAccountList.getRole().trim().toLowerCase().equals("user"))
        			  return 3;
            }
        }
        return 0;
        
    }

    
    
    public List<UserAccount> findAllUser() {
        List<UserAccount> userAccountList = (List<UserAccount>) userAccountRepository.findAll();
        if (userAccountList != null) {
            return userAccountList;
        }
        return null;
    }

    
    public UserAccount findByEmployeeId(long employeeId) {
        UserAccount userAccountList = userAccountRepository.findByEmployeeId(employeeId);
        if (userAccountList != null) {
           return userAccountList;
        }
        return null;
    }

   
    public String updateUser(UserAccount useraccount) {

        UserAccount userAccountList = userAccountRepository.findByEmployeeId(useraccount.getEmployeeId());

        if (userAccountList != null) {            
        	userAccountList.setEmployeeId(useraccount.getEmployeeId());
        	userAccountList.setPassword(useraccount.getPassword());
            userAccountRepository.save(userAccountList);
            return ("User data update successfully.");            
        }
        return "No record found";
    }
    
}
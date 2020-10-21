package com.ems.attendance.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ems.attendance.config.ConfigUtility;
import com.ems.attendance.entity.EmployeeDetail;
@Service
public class MailService {
@Autowired
public static Environment env;
@Autowired

public static void main(String[] args) {
 //   sendFromGMail(from, pass, to, subject, body);
    //sendmail();
    //sendMail2();
    }

public void sendMailUser(int type,EmployeeDetail e) throws IOException{

		String to = e.getMailID();
	    // Sender's email ID needs to be mentioned
	   // String from = "ssitsoltest@gmail.com";
		 String from = "emsadmin@ssitsol.com";
		 String pwd="Ssit444@";
	    // Assuming you are sending email from through gmails smtp
	    String host = "smtp.gmail.com";
	    //String pwd="123#@ssit";
	    String port ="465";
	    System.out.println("email from----------->"+from);
	    System.out.println("email to----------->"+to);
	    String body="";
 	    String subject="";
    // subje you are sending email from through gmails smtp
 	   String finalgreetingname = e.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + e.getFirstName().toLowerCase().substring(1);
	    
if(type==1){
	subject="New Employee Registered Successfully";
	body="\r\n  Hi "+finalgreetingname+", \r\n \r\n your ems account is active now .Below are the login credentials .\n\r userId :"+e.getAdUser()+" \r\n Password:"+e.getPassword();
    
}
else{
	subject="Employee details updated Successfully- Employee ID:"+e.getEmployeeId();
	body="\r\n  Hi "+finalgreetingname+", \r\n \r\n your ems account details are updated and the account is active now .Below are the login credentials .\n\r userId :"+e.getAdUser()+" \r\n Password:"+e.getPassword();
}
body=body+"\r\n \r\n \r\n "+"Regards,"+"\r\n EMS Admin";
    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
    properties.put("mail.smtp.socketFactory.class",    
            "javax.net.ssl.SSLSocketFactory");    
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port",port);
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    // Get the Session object.// and pass username and password
    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {

            return new PasswordAuthentication(from,pwd);

        }

    });

    // Used to debug SMTP issues
    session.setDebug(true);

    try {
        // Create a default MimeMessage object.
        MimeMessage message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // Set Subject: header field
        message.setSubject(subject);

        // Now set the actual message
        message.setText(body);

        System.out.println("sending...");
        // Send message
        Transport.send(message);
        System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
        mex.printStackTrace();
    }

}


public void sendMailLeave(int type,EmployeeDetail mgr,EmployeeDetail e,int reqNo) throws IOException{

	String to = mgr.getMailID();
	String cc = e.getMailID();
    // Sender's email ID needs to be mentioned
   // String from = "ssitsoltest@gmail.com";
	 String from = "emsadmin@ssitsol.com";
	 String pwd="Ssit444@";
    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";
   // String pwd="123#@ssit";
    String port ="465";
    System.out.println("email from----------->"+from);
    System.out.println("email to----------->"+to);
    String body="";
	    String subject="";
	    String finalgreetingname = mgr.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + mgr.getFirstName().toLowerCase().substring(1);
	    String empname = e.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + e.getFirstName().toLowerCase().substring(1);
// subje you are sending email from through gmails smtp

	if(type==1){
	subject="Leave approval request";
	body="\r\n  Hi "+finalgreetingname+", \r\n \r\n The leave request is submitted by  "+empname+"("+e.getEmployeeId()+")";
	
	}
	
	body=body+"\r\n \r\n \r\n "+"Regards,"+"\r\n EMS Admin";
	// Get system properties
	Properties properties = System.getProperties();

// Setup mail server
properties.put("mail.smtp.socketFactory.class",    
        "javax.net.ssl.SSLSocketFactory");    
properties.put("mail.smtp.host", host);
properties.put("mail.smtp.port",port);
properties.put("mail.smtp.ssl.enable", "true");
properties.put("mail.smtp.auth", "true");

// Get the Session object.// and pass username and password
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(from,pwd);

    }

});

// Used to debug SMTP issues
session.setDebug(true);

try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(from));

    // Set To: header field of the header.
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    
    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

    // Set Subject: header field
    message.setSubject(subject);

    // Now set the actual message
    message.setText(body);

    System.out.println("sending...");
    // Send message
    Transport.send(message);
    System.out.println("Sent message successfully....");
} catch (MessagingException mex) {
    mex.printStackTrace();
}

}

public void sendMailTimesheet(int type,EmployeeDetail mgr,EmployeeDetail e) throws IOException{

	String to = mgr.getMailID();
	String cc = e.getMailID();
    // Sender's email ID needs to be mentioned
	//imap.gmail.com
	//993  emsadmin@ssitsol.com  Ssit444@

    String from = "emsadmin@ssitsol.com";
    //String from = "ssitsoltest@gmail.com";
    // Assuming you are sending email from through gmails smtp
    //String host = "imap.gmail.com";
    String host = "smtp.gmail.com";
    //String pwd="123#@ssit";
    String pwd="Ssit444@";
    String port ="465";
    //String port ="993";
    System.out.println("email from----------->"+from);
    System.out.println("email to----------->"+to);
    String body="";
	    String subject="";
	    String finalgreetingname = mgr.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + mgr.getFirstName().toLowerCase().substring(1);
	    String reporteename = e.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + e.getFirstName().toLowerCase().substring(1); 

// subje you are sending email from through gmails smtp

	if(type==1){
	subject="Timesheet approval request ";
	body="\r\n  Hi "+finalgreetingname+", \r\n \r\n The Timsheet request  is submitted by  "+reporteename+"("+e.getEmployeeId()+")";
	
	}
	
	body=body+"\r\n \r\n \r\n "+"Regards,"+"\r\n EMS Admin";
	// Get system properties
	Properties properties = System.getProperties();

// Setup mail server
properties.put("mail.smtp.socketFactory.class",    
        "javax.net.ssl.SSLSocketFactory");    
properties.put("mail.smtp.host", host);
properties.put("mail.smtp.port",port);
properties.put("mail.smtp.ssl.enable", "true");
properties.put("mail.smtp.auth", "true");

// Get the Session object.// and pass username and password
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(from,pwd);

    }

});

// Used to debug SMTP issues
session.setDebug(true);

try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(from));

    // Set To: header field of the header.
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    
    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

    // Set Subject: header field
    message.setSubject(subject);

    // Now set the actual message
    message.setText(body);

    System.out.println("sending...");
    // Send message
    Transport.send(message);
    System.out.println("Sent message successfully....");
} catch (MessagingException mex) {
    mex.printStackTrace();
}

}


//Approve n reject mail
public void sendstatusLeaveMail(int type,EmployeeDetail mgr,EmployeeDetail e,int reqNo,String status) throws IOException{

	String cc = mgr.getMailID();
	String to = e.getMailID();
    // Sender's email ID needs to be mentioned
   // String from = "ssitsoltest@gmail.com";
	 String from = "emsadmin@ssitsol.com";
	 String pwd="Ssit444@";
    String fullname="";
   
    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";
 //   String pwd="123#@ssit";
    String port ="465";
    System.out.println("email from----------->"+from);
    System.out.println("email to----------->"+to);
    String body="";
	    String subject="";
// subje you are sending email from through gmails smtp
	  
	    String empname = e.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + e.getFirstName().toLowerCase().substring(1);

	if(type==1){
		if(mgr!=null){
			if(mgr.getFirstName()!=null)
				fullname=fullname+mgr.getFirstName().toLowerCase();
			if(mgr.getMiddleName()!=null)
				fullname=fullname+" "+mgr.getMiddleName().toLowerCase();
			
		}  
		String finalfullname = fullname.toLowerCase().substring(0, 1).toUpperCase() +fullname.toLowerCase().substring(1);
	subject="Leave Request status for request number:"+reqNo;
	body="\r\n  Hi "+empname+", \r\n \r\n The leave request request number: "+reqNo+" is "+status.toUpperCase()+" by  "+finalfullname+"("+mgr.getEmployeeId()+")";
	
	}
	
	body=body+"\r\n \r\n \r\n "+"Regards,"+"\r\n EMS Admin";
	// Get system properties
	Properties properties = System.getProperties();

// Setup mail server
properties.put("mail.smtp.socketFactory.class",    
        "javax.net.ssl.SSLSocketFactory");    
properties.put("mail.smtp.host", host);
properties.put("mail.smtp.port",port);
properties.put("mail.smtp.ssl.enable", "true");
properties.put("mail.smtp.auth", "true");

// Get the Session object.// and pass username and password
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(from,pwd);

    }

});

// Used to debug SMTP issues
session.setDebug(true);

try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(from));

    // Set To: header field of the header.
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    
    message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

    // Set Subject: header field
    message.setSubject(subject);

    // Now set the actual message
    message.setText(body);

    System.out.println("sending...");
    // Send message
    Transport.send(message);
    System.out.println("Sent message successfully....");
} catch (MessagingException mex) {
    mex.printStackTrace();
}

}

//Approve n reject mail
public void sendstatusTimesheet(int type,EmployeeDetail mgr,EmployeeDetail e,String status) throws IOException{

	String cc = mgr.getMailID();
	String to = e.getMailID(); 
  // Sender's email ID needs to be mentioned
  //String from = "ssitsoltest@gmail.com";
	 String from = "emsadmin@ssitsol.com";
	 String pwd="Ssit444@";
 String fullname="";
  // Assuming you are sending email from through gmails smtp
  String host = "smtp.gmail.com";
//  String pwd="123#@ssit";
  String port ="465";
  System.out.println("email from----------->"+from);
  System.out.println("email to----------->"+to);
  String body="";
  String subject="";
//subje you are sending email from through gmails smtp

	if(type==1){
	subject="TimeSheet Request status";
	if(mgr!=null){
		if(mgr.getFirstName()!=null)
			fullname=fullname+mgr.getFirstName().toLowerCase();
		if(mgr.getMiddleName()!=null)
			fullname=fullname+" "+mgr.getMiddleName().toLowerCase();
		
	}
    String empname = e.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + e.getFirstName().toLowerCase().substring(1);
	String finalfullname = fullname.toLowerCase().substring(0, 1).toUpperCase() +fullname.toLowerCase().substring(1);

	body="\r\n  Hi "+empname+", \r\n \r\n The Time sheet request  is "+status.toUpperCase()+" by  "+finalfullname+"("+mgr.getEmployeeId()+")";
	
	}
	
	body=body+"\r\n \r\n \r\n "+"Regards,"+"\r\n EMS Admin";
	// Get system properties
	Properties properties = System.getProperties();

//Setup mail server
properties.put("mail.smtp.socketFactory.class",    
      "javax.net.ssl.SSLSocketFactory");    
properties.put("mail.smtp.host", host);
properties.put("mail.smtp.port",port);
properties.put("mail.smtp.ssl.enable", "true");
properties.put("mail.smtp.auth", "true");

//Get the Session object.// and pass username and password
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

  protected PasswordAuthentication getPasswordAuthentication() {

      return new PasswordAuthentication(from,pwd);

  }

});

//Used to debug SMTP issues
session.setDebug(true);

try {
  // Create a default MimeMessage object.
  MimeMessage message = new MimeMessage(session);

  // Set From: header field of the header.
  message.setFrom(new InternetAddress(from));

  // Set To: header field of the header.
  message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
  
  message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

  // Set Subject: header field
  message.setSubject(subject);

  // Now set the actual message
  message.setText(body);

  System.out.println("sending...");
  // Send message
  Transport.send(message);
  System.out.println("Sent message successfully....");
} catch (MessagingException mex) {
  mex.printStackTrace();
}

}
public void sendpwdResetMail(String emailID,String empname) throws IOException{

	String to = emailID;
	//String cc = e.getMailID();
    // Sender's email ID needs to be mentioned
   // String from = "ssitsoltest@gmail.com";
	 String from = "emsadmin@ssitsol.com";
	 String pwd="Ssit444@";
    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";
   // String pwd="123#@ssit";
    String port ="465";
    System.out.println("email from----------->"+from);
    System.out.println("email to----------->"+to);
    String body="";
	    String subject="";
	   // String finalgreetingname = mgr.getFirstName().toLowerCase().substring(0, 1).toUpperCase() + mgr.getFirstName().toLowerCase().substring(1);
	    empname = empname.substring(0, 1).toUpperCase() +empname.toLowerCase().substring(1);
// subje you are sending email from through gmails smtp

	//if(type==1)
	{
	subject="Password Reset Successfull";
	body="\r\n  Hi "+empname+", \r\n \r\n The Password reset is successfull, please login again to continue";
	
	}
	
	body=body+"\r\n \r\n \r\n "+"Regards,"+"\r\n EMS Admin";
	// Get system properties
	Properties properties = System.getProperties();

// Setup mail server
properties.put("mail.smtp.socketFactory.class",    
        "javax.net.ssl.SSLSocketFactory");    
properties.put("mail.smtp.host", host);
properties.put("mail.smtp.port",port);
properties.put("mail.smtp.ssl.enable", "true");
properties.put("mail.smtp.auth", "true");

// Get the Session object.// and pass username and password
Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(from,pwd);

    }

});

// Used to debug SMTP issues
session.setDebug(true);

try {
    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set From: header field of the header.
    message.setFrom(new InternetAddress(from));

    // Set To: header field of the header.
    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    
   // message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));

    // Set Subject: header field
    message.setSubject(subject);

    // Now set the actual message
    message.setText(body);

    System.out.println("sending...");
    // Send message
    Transport.send(message);
    System.out.println("Sent message successfully....");
} catch (MessagingException mex) {
    mex.printStackTrace();
}

}


 } 

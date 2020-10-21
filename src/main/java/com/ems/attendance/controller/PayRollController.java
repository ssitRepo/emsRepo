package com.ems.attendance.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;

import com.itextpdf.html2pdf.HtmlConverter;
import com.ems.attendance.entity.PayRoll;
import com.ems.attendance.entity.PaySlipRequest;
import com.ems.attendance.entity.User;
import com.ems.attendance.service.LeaveService;
import com.ems.attendance.service.PayRollService;
import com.ems.attendance.service.UserAccountService;

@Controller
@RequestMapping(path="/payroll")
public class PayRollController {

	 @Autowired
	 PayRollService payRollService;
	 
	 @Autowired
	 UserAccountService userAccountService;
	 
	@GetMapping(path = "/getpayslip")
    @CrossOrigin()
    @ResponseBody
    public void getpayslip(HttpServletRequest req,HttpServletResponse res,@RequestParam int empid,@RequestParam String month,@RequestParam int year) {
    	HttpHeaders header = new HttpHeaders();
    	
        try {
        	//String month="october";
        //	int empid=payslipreq.getEmployeeID();
//        	String pwd=payslipreq.getPassword();

        	//checking for valid user
        	//long status=userAccountService.CheckUser(empid,pwd);
        	 System.out.println("month-"+month);
        	 System.out.println("empid-"+empid);
        	 System.out.println("year-"+year);

        	//if(status>0)
        	{
        //	String month=payslipreq.getMonth();
        	month=month.substring(0,1).toUpperCase()+month.substring(1);
        	String dispmonth=month.substring(0,3);
        	//int year=2020;
        	//int year=payslipreq.getYear();
        	String newdoj="";
        	String newpaydate="";
        	String startdate=null;
        	String enddate=null;
        	String finalpayperiod=null;
        	String newPan=null;
        	String newUAN=null;
        	String newPFnumber=null;
        	String newLocation=null;
        	YearMonth yearMonthObject = YearMonth.of(year, 10);
        	int daysInMonth = yearMonthObject.lengthOfMonth(); //28  
        	
        	startdate="01/"+dispmonth+"/"+year;
        	enddate=daysInMonth+"/"+dispmonth+"/"+year;		
        	finalpayperiod= startdate +" - "+enddate;
        	SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-YY");
        	SimpleDateFormat sf1=new SimpleDateFormat("MM/dd/YYYY");
        	
        	//PayRoll p=payRollService.findByMonthYear(10023, "October", 2020);
        	PayRoll p=payRollService.findByMonthYear(empid, month, year);
        	String HTML="";
        	if(p!=null){
        	 System.out.println( "PDF Created!" +p.getBasicSalary());
          	 System.out.println("olddoj-"+p.getDOJ());
          	 if(p.getPANNo()!=null)
          		 newPan=p.getPANNo().toUpperCase();
          	 if(p.getUAN()!=null)
          		newUAN=p.getUAN().toUpperCase();
          	 if(p.getPFNumber()!=null)
          		newPFnumber=p.getPFNumber().toUpperCase();
          	 if(p.getLocation()!=null)
          		newLocation=p.getLocation().substring(0,1).toUpperCase()+p.getLocation().substring(1).toLowerCase();
          	 if(p.getDOJ()!=null)
          		 newdoj=sf.format(p.getDOJ());
          	 if(p.getPayDate()!=null)
        	 newpaydate=sf1.format(p.getPayDate());
          	 
         // 	String newnetpay=String.format("%,d", p.getNetPay());
          	//DecimalFormat formatter = new DecimalFormat("#,###.00");

        //  	System.out.println(formatter.format(p.getNetPay()));
          	String newnetpay=formatdouble(p.getNetPay());
          	 
        	 System.out.println("newdoj-"+newdoj);
        	 System.out.println("newpaydate-"+newpaydate);
        	 
        	
        	 HTML = "<HTML><style>"
				        +"#customers {"
				        +"font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;"
				        +" border-collapse: collapse;"
				         +" width: 100%;"
				         +" }"
				        +"#customers td, #customers th {"
				         +" border: 1px  solid #ddd;font-size:12px"
				          +"padding: 8px;"
				          +"}"
				        
				        +"#customers tr:nth-child(even){background-color: #f2f2f2;}"
				        
				        +"#customers tr:hover {background-color: #ddd;}"
				        
				        +"#customers th {"
				          +"padding-top: 12px;"
				          +"padding-bottom: 12px;"
				          +"text-align: left;"
				         +" //background-color: #4CAF50;"
				          +"color: white;"
				          +"}"
				        +"</style>"
         			+ "<BODY><div><img style=\"height: 50px;\" src=\"http://img1.wsimg.com/isteam/ip/3d1c006b-fcb8-49f7-a249-d2d105da9b83/logo2.jpg\"></div>"
         			+ "<TABLE BORDER=1 width=100% id=customers><TR><TD COLSPAN=4 align=center>SIGMASOFT INFOTECH PRIVATE LIMITED <BR> 26-256/125, BALRAM NAGAR, SAFILGUDA, HYDERABAD, TELANGANA-500047<BR><BR>"
         			+ "</TD></TR>"
         			+ "<tr >"
         			+"<td colspan=4 align=center>PAY SLIP For the month of "+month+"-"+year+" </td>"
         			+"</tr><tr>"
         			+"<td><b>Employee ID </b></td>"
               +"<td>"+p.getEmployee_ID()+"</td>"
               +"<td><b>&nbsp;&nbsp;Pay Period</b> </td>"
               +"<td align=right>"+finalpayperiod+"</td>"
             +"</tr>"
            +" <tr><td><b>Employee Name</b></td><td>"+p.getEmployeeName()+"</td><td><b>&nbsp;&nbsp;Pay Date </b></td>"
            +"   <td align=right>"+newpaydate+"</td> </tr> <tr>"
              +"   <td><b>Designation</b> </td> <td>"+p.getDesignation()+"</td> <td>&nbsp;&nbsp;<b>DOJ </b></td>"
             +"    <td align=right>"+newdoj+"</td>"
               +"</tr><tr> <td><b>Location </b></td><td>"+newLocation+"</td><td><b>&nbsp;&nbsp;No Of Days </b></td><td align=right>"+p.getNoOfDays()+"</td></tr>"
              +" <tr><td><b>PF Number</b> </td><td>"+newPFnumber+"</td><td><b>&nbsp;&nbsp;UAN</b> </td><td align=right>"+newUAN+"</td>"
                 +"</tr><tr> <td><b>PAN No</b></td><td>"+newPan+"</td><td><b>&nbsp;&nbsp;LOP</b></td><td align=right>"+p.getLOP()+"</td></tr>"
           +"<tr><td colspan=4>&nbsp;</td></tr><tr >"
                 +"<td colspan=2 align=center ><b>Earnings</b></td>"
                 +" <td colspan=2 align=center><b>Deductions</b></td></tr>"
               +"<tr align=right> <td>Basic Salary </td> <td align=right>"+formatdouble(p.getBasicSalary())+"</td> <td>&nbsp;&nbsp;Professional Tax </td> <td align=right>"+formatdouble( p.getProfessionalTax())+"</td>"
                 +"</tr><tr><td>HRA</td><td align=right>"+formatdouble(p.getHRA())+"</td>"
                  +" <td>&nbsp;&nbsp;PF</td><td align=right>"+formatdouble(p.getPF())+"</td></tr><tr>"
                     +"<td>Conveyance Allowance</td>"
                     +"<td align=right>"+formatdouble(p.getConveyanceAllowance())+"</td>"
                     +"<td>&nbsp;&nbsp;LOP </td>"
                     +"<td align=right>"+formatdouble(p.getLOP())+"</td> </tr> <tr><td>Medical Allowance </td><td align=right>"+formatdouble(p.getMedicalAllowance())+"</td>"
                     +"  <td >&nbsp;&nbsp;Income Tax </td><td align=right>"+formatdouble(p.getIncomeTax())+"</td> </tr> <tr><td>Special Allowance </td>"
                     +"  <td align=right>"+formatdouble(p.getSpecialAllowance())+"</td> <td>&nbsp; </td> <td>&nbsp;</td> </tr>"
                     +"<tr><td>Other Allowance </td><td align=right>"+formatdouble(p.getOtherAllowance())+"</td> <td>&nbsp; </td><td>&nbsp;</td></tr>"
                      +"<tr><td>Variable Pay </td><td align=right>"+formatdouble(p.getVariablePay())+"</td><td>&nbsp; </td><td>&nbsp;</td> </tr>"
                      +"<tr><td colspan=4>&nbsp;</td></tr><tr><td>Total Earnings</td><td align=right>"+formatdouble(p.getTotalEarnings())+"</td><td>&nbsp;&nbsp;Total Deductions </td><td align=right>"+formatdouble(p.getTotalDeductions())+"</td> </tr>"
                       +"<tr><td>Net Pay </td><td align=right>"+newnetpay+"</td>"
                       +"      <td>&nbsp; </td>  <td>&nbsp;</td>      </tr> </table><br><br>This is electronically generated and no signature is required"
         			+ "</BODY></HTML";
        	}
        	else
        		HTML="PaySlip Not Available";
        	 String filename=empid+"_"+dispmonth+"_"+year+".pdf";
        	 System.out.println("--->filename-->"+filename);
        	res.setContentType("application/pdf");
        	res.setHeader("Content-Disposition", "attachment; filename="+filename);
        	//res.setHeader("Content-Disposition", "attachment; filename=\"salaryslip.pdf\"");
        //	res.setHeader(header);
        	ServletOutputStream f= res.getOutputStream();
        	
        	HtmlConverter.convertToPdf(HTML,f);
        	//ServletOutputStream f = res.getOutputStream();
        	res.flushBuffer();
        	
            System.out.println( "PDF Created!" );
        	}
    }catch (Exception e) {  
		// TODO: handle exception
    	//headers.add("Message", "false");
    	e.printStackTrace();
    	//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to check the user");
	}
	//	return null;
    }
	private String formatdouble(double value){
		DecimalFormat formatter = new DecimalFormat("#,###.00");
		try{
			return formatter.format(value);
		}
		catch(Exception e){
			return "0.00";
		}
		
	}
	
	@GetMapping(path = "/viewpayslip")
    @CrossOrigin()
    @ResponseBody
    public PayRoll viewpayslip(@RequestParam int empid,@RequestParam String month,@RequestParam int year) {
    	HttpHeaders header = new HttpHeaders();
    	
        try {
        	//String month="october";
        //	int empid=payslipreq.getEmployeeID();
//        	String pwd=payslipreq.getPassword();

        	//checking for valid user
        	//long status=userAccountService.CheckUser(empid,pwd);
        	 System.out.println("month-"+month);
        	 System.out.println("empid-"+empid);
        	 System.out.println("year-"+year);

        	//if(status>0)
        	
        //	String month=payslipreq.getMonth();
        	month=month.substring(0,1).toUpperCase()+month.substring(1);
        	String dispmonth=month.substring(0,3);
        	//int year=2020;
        	//int year=payslipreq.getYear();
        	String newdoj="";
        	String newpaydate="";
        	String startdate=null;
        	String enddate=null;
        	String finalpayperiod=null;
        	String newPan=null;
        	String newUAN=null;
        	String newPFnumber=null;
        	String newLocation=null;
        	YearMonth yearMonthObject = YearMonth.of(year, 10);
        	int daysInMonth = yearMonthObject.lengthOfMonth(); //28  
        	
        	startdate="01/"+dispmonth+"/"+year;
        	enddate=daysInMonth+"/"+dispmonth+"/"+year;		
        	finalpayperiod= startdate +" - "+enddate;
        	SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-YY");
        	SimpleDateFormat sf1=new SimpleDateFormat("MM/dd/YYYY");
        	
        	//PayRoll p=payRollService.findByMonthYear(10023, "October", 2020);
        	PayRoll p=payRollService.findByMonthYear(empid, month, year);
        	String HTML="";
        	System.out.println("==>"+p);
        	if(p!=null){
        	 System.out.println( "PDF Created!" +p.getBasicSalary());
          	 System.out.println("olddoj-"+p.getDOJ());
          	 if(p.getPANNo()!=null)
          		 newPan=p.getPANNo().toUpperCase();
          	 if(p.getUAN()!=null)
          		newUAN=p.getUAN().toUpperCase();
          	 if(p.getPFNumber()!=null)
          		newPFnumber=p.getPFNumber().toUpperCase();
          	 if(p.getLocation()!=null)
          		newLocation=p.getLocation().substring(0,1).toUpperCase()+p.getLocation().substring(1).toLowerCase();
          	 if(p.getDOJ()!=null)
          		 newdoj=sf.format(p.getDOJ());
          	 if(p.getPayDate()!=null)
        	 newpaydate=sf1.format(p.getPayDate());
          	 
         // 	String newnetpay=String.format("%,d", p.getNetPay());
          	//DecimalFormat formatter = new DecimalFormat("#,###.00");

          //	System.out.println(formatter.format(p.getNetPay()));
          	String newnetpay=formatdouble(p.getNetPay());
          	 p.setPANNo(newPan);
          	 p.setUAN(newUAN);
          	 p.setPFNumber(newPFnumber);
          	 p.setLocation(newLocation);
          	 
        	 System.out.println("newdoj-"+newdoj);
        	 System.out.println("newpaydate-"+newpaydate);
        	 return p;
        	}
        	else
        		return null;
        	
        	
    }catch (Exception e) {  
		// TODO: handle exception
    	//headers.add("Message", "false");
    	e.printStackTrace();
    	//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body("Failed to check the user");
	}
	//	return null;
		return null;
    }

}

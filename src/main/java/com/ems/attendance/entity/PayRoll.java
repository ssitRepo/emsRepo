package com.ems.attendance.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


////`TotalEarnings`, `VariablePay` FROM `ssitdb`.`payroll`;


@Entity(name="payroll")
public class PayRoll {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
private long Employee_ID;
private String EmployeeName;
private String Designation;
private String Location;
private String PANNo;
private Date PayDate;
private Date DOJ;
private int NoOfDays;
private double LOP;
private double BasicSalary;
private double HRA;
private double ConveyanceAllowance;
private double MedicalAllowance;
private double SpecialAllowance;
private double OtherAllowance;
private double VariablePay;
private double ProfessionalTax;
private double IncomeTax;
private double TotalEarnings;
private double TotalDeductions;
private double NetPay;
private String PayPeriodMonth;
private int PayPeriodYear;
private double LossOfPay;
private String UAN;
private String PFNumber;
private double PF;
public String getUAN() {
	return UAN;
}
public void setUAN(String uAN) {
	UAN = uAN;
}
public String getPFNumber() {
	return PFNumber;
}
public void setPFNumber(String pFNumber) {
	PFNumber = pFNumber;
}
public double getPF() {
	return PF;
}
public void setPF(double pF) {
	PF = pF;
}
public String getPANNo() {
	return PANNo;
}
public void setPANNo(String pANNo) {
	PANNo = pANNo;
}
public int getNoOfDays() {
	return NoOfDays;
}
public void setNoOfDays(int noOfDays) {
	NoOfDays = noOfDays;
}
public double getLossOfPay() {
	return LossOfPay;
}
public void setLossOfPay(double lossOfPay) {
	LossOfPay = lossOfPay;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public long getEmployee_ID() {
	return Employee_ID;
}
public void setEmployee_ID(long employee_ID) {
	Employee_ID = employee_ID;
}
public String getEmployeeName() {
	return EmployeeName;
}
public void setEmployeeName(String employeeName) {
	EmployeeName = employeeName;
}
public String getDesignation() {
	return Designation;
}
public void setDesignation(String designation) {
	Designation = designation;
}
public String getLocation() {
	return Location;
}
public void setLocation(String location) {
	Location = location;
}



public Date getPayDate() {
	return PayDate;
}
public void setPayDate(Date payDate) {
	PayDate = payDate;
}
public Date getDOJ() {
	return DOJ;
}
public void setDOJ(Date dOJ) {
	DOJ = dOJ;
}
public double getLOP() {
	return LOP;
}
public void setLOP(double lOP) {
	LOP = lOP;
}
public double getBasicSalary() {
	return BasicSalary;
}
public void setBasicSalary(double basicSalary) {
	BasicSalary = basicSalary;
}
public double getHRA() {
	return HRA;
}
public void setHRA(double hRA) {
	HRA = hRA;
}
public double getConveyanceAllowance() {
	return ConveyanceAllowance;
}
public void setConveyanceAllowance(double conveyanceAllowance) {
	ConveyanceAllowance = conveyanceAllowance;
}
public double getMedicalAllowance() {
	return MedicalAllowance;
}
public void setMedicalAllowance(double medicalAllowance) {
	MedicalAllowance = medicalAllowance;
}
public double getSpecialAllowance() {
	return SpecialAllowance;
}
public void setSpecialAllowance(double specialAllowance) {
	SpecialAllowance = specialAllowance;
}
public double getOtherAllowance() {
	return OtherAllowance;
}
public void setOtherAllowance(double otherAllowance) {
	OtherAllowance = otherAllowance;
}
public double getVariablePay() {
	return VariablePay;
}
public void setVariablePay(double variablePay) {
	VariablePay = variablePay;
}
public double getProfessionalTax() {
	return ProfessionalTax;
}
public void setProfessionalTax(double professionalTax) {
	ProfessionalTax = professionalTax;
}
public double getIncomeTax() {
	return IncomeTax;
}
public void setIncomeTax(double incomeTax) {
	IncomeTax = incomeTax;
}
public double getTotalEarnings() {
	return TotalEarnings;
}
public void setTotalEarnings(double totalEarnings) {
	TotalEarnings = totalEarnings;
}
public double getTotalDeductions() {
	return TotalDeductions;
}
public void setTotalDeductions(double totalDeductions) {
	TotalDeductions = totalDeductions;
}
public double getNetPay() {
	return NetPay;
}
public void setNetPay(double netPay) {
	NetPay = netPay;
}
public String getPayPeriodMonth() {
	return PayPeriodMonth;
}
public void setPayPeriodMonth(String payPeriodMonth) {
	PayPeriodMonth = payPeriodMonth;
}
public int getPayPeriodYear() {
	return PayPeriodYear;
}
public void setPayPeriodYear(int payPeriodYear) {
	PayPeriodYear = payPeriodYear;
}
}
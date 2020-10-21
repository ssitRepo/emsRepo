package com.ems.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@PropertySource("classpath:application.properties")
public class AttendanceApplication extends SpringBootServletInitializer{

public static void main(String[] args) {
	System.out.println("main method...............");
SpringApplication.run(AttendanceApplication.class, args);
}
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder sp)
{
	System.out.println("override method...............");
	return sp.sources(AttendanceApplication.class);


}


}
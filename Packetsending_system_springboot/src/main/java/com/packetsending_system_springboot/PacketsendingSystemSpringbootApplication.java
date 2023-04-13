package com.packetsending_system_springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@SpringBootApplication
public class PacketsendingSystemSpringbootApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =  SpringApplication.run(PacketsendingSystemSpringbootApplication.class, args);
		
		String[] beans = ctx.getBeanDefinitionNames();
		
		Arrays.sort(beans);
		
		for(String b : beans) {
			System.out.println(b);
		}
	}

}

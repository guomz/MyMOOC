package com.guomz.MyMOOC;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.guomz")
@MapperScan("com.guomz.MyMOOC.dao")
@EnableTransactionManagement
public class MyMoocApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMoocApplication.class, args);
	}

}

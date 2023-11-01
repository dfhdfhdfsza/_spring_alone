package com.myweb.www.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(basePackages = {"com.myweb.www.repository"})
@ComponentScan(basePackages = {"com.myweb.www.service","com.myweb.www.handler"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class RootConfig 
{
	//DB 설정 부분
	//전과 달라진 부분 log4jdbc-log4j2 사용
	//hikariCP 사용
	
	@auto

}

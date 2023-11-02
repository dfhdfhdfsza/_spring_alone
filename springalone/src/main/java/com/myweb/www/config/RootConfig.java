package com.myweb.www.config;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"com.myweb.www.repository"})
@ComponentScan(basePackages = {"com.myweb.www.service"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class RootConfig 
{
	//DB 설정 부분
	//전과 달라진 부분 log4jdbc-log4j2 사용
	//hikariCP 사용
	
	@Autowired //inject랑 똑같은데 내가 설정한 객체가 아닐경우
	ApplicationContext applicationcontext;

	@Bean
	public DataSource dataSource()
	{
		HikariConfig hikariConfig = new HikariConfig();
		//log4jdbc-log4j2의 드라이버 클래스 url 사용
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springalone");
		hikariConfig.setUsername("springUser");
		hikariConfig.setPassword("mysql");
		
		hikariConfig.setMaximumPoolSize(5); //최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); //최소 유휴 커넥션 개수(반드시 MaximumPoolSize)와 같은 값으로 설정)
		hikariConfig.setConnectionTestQuery("SELECT now()"); //test 쿼리
		hikariConfig.setPoolName("springHikariCP"); //pool name 설정
		
		//config의 추가 설정
		
		//캐시사용여부설정 => 속도 향상
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts","true");
		//mysql 드라이버가 연결당 cache statement의 수에 관한 설정 기본값:25 250~500 권장
		hikariConfig.addDataSourceProperty("dataSource.PrepStmtsCacheSize","250");
		//mysql 드라이버 connection당 캐싱할 preparedStatement의 개수 지정 옵션 default 256 =="true"
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit","true");
		//mysql 서버에서 최신 이슈가 있을 경우 지원받는 설정 server-side 지원 설정 true
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts","true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory()throws Exception
	{
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(applicationcontext.getResources("classpath:/mappers/*.xml"));
		sqlFactoryBean.setConfigLocation(applicationcontext.getResource("classpath:/MybatisConfig.xml"));
		return (SqlSessionFactory)sqlFactoryBean.getObject();
	}
	//트랙젝션매니저 빈 설정
	@Bean
	public DataSourceTransactionManager transactionManger()
	{
		return new DataSourceTransactionManager(dataSource());
	}

}

package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ServletConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() 
	{
		//encoding filter 설정
		CharacterEncodingFilter encodingfilter=new CharacterEncodingFilter();
		encodingfilter.setEncoding("UTF-8"); //"UTF-8" 타입만 받는다는 설정
		encodingfilter.setForceEncoding(true); //기본적으로는 들어오는 값만 필터링해주는데 forceEncoding은 나갈때도 필터링해줌
		return new Filter[] {encodingfilter};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 그 외 기타 사용자 설정
		
	}
	
}

package com.myweb.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
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
		
		//파일 업로드 설정
		//경로,maxFileSize,maxReqSize,fileSizeThreshold
		String uploadLocation="D:\\_myweb\\_java_alone\\fileUpload";
		int maxFileSize=1024*1024*20; //20mb
		int maxReqSize=maxFileSize*2;
		int fileSizeThreshold=maxFileSize;
		
		MultipartConfigElement multipartConfig=new MultipartConfigElement(uploadLocation, maxFileSize, maxReqSize, fileSizeThreshold);
		
		registration.setMultipartConfig(multipartConfig);
		
	}
	
}

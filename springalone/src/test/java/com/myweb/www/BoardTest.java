package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.domain.boardVO;
import com.myweb.www.repository.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.myweb.www.config.RootConfig.class})
public class BoardTest 
{
	@Inject
	private BoardDAO bdao;
	
	@Test
	public void insertBoard()
	{
		for(int i=0;i<300;i++)
		{
			boardVO bvo = new boardVO();
			bvo.setTitle("Test Title"+i);
			bvo.setWriter("Test Writer");
			bvo.setContent("Test Content"+i);
			bdao.register(bvo);
		}
	}
}
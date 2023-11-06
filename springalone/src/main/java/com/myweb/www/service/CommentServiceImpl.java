package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.commentVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService
{
	@Inject
	private CommentDAO cdao;

	@Override
	public int post(commentVO cvo) {
		return cdao.post(cvo);
	}

//	@Override
//	public List<commentVO> list(int bno) {
//		return cdao.list(bno);
//	}

	@Override
	public int delete(int cno) {
		
		return cdao.delete(cno);
	}

	@Override
	public int update(commentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public PagingHandler list(int bno, PagingVO pgvo) {
		int totalCount=cdao.totalCount(bno);
		List<commentVO> list=cdao.list(bno,pgvo);
		PagingHandler ph=new PagingHandler(pgvo, totalCount, list);
		return  ph;
	}

}

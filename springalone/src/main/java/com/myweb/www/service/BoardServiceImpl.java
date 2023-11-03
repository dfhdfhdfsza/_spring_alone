package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.boardVO;
import com.myweb.www.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService
{
	@Inject
	private BoardDAO bdao;

	@Override
	public int register(boardVO bvo) {
		return bdao.register(bvo);
	}

//	@Override
//	public List<boardVO> list() {
//		return bdao.list();
//	}
	
	@Override
	public List<boardVO> list(PagingVO pgvo) {
		return bdao.pagelist(pgvo);
	}

	@Override
	public boardVO detail(int bno) {
		bdao.rcUp(bno);
		return bdao.detail(bno);
	}

	@Override
	public int modify(boardVO bvo) {
		return bdao.modify(bvo);
	}

	@Override
	public int remove(int bno) {
		return bdao.remove(bno);
	}

	@Override
	public int totalCount(PagingVO pgvo) {
		return bdao.totalCount(pgvo);
	}

	

}

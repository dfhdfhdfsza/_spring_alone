package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.boardVO;

public interface BoardService {

	int register(boardVO bvo);

//	List<boardVO> list();

	boardVO detail(int bno);

	int modify(boardVO bvo);

	int remove(int bno);

	List<boardVO> list(PagingVO pgvo);

	int totalCount();

}

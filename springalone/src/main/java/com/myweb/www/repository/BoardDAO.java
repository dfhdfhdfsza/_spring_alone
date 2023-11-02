package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.boardVO;

public interface BoardDAO {

	int register(boardVO bvo);

//	List<boardVO> list();

	boardVO detail(int bno);

	int modify(boardVO bvo);

	int remove(int bno);

	void rcUp(int bno);

	List<boardVO> pagelist(PagingVO pgvo);

	int totalCount();

}

package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.commentVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int post(commentVO cvo);

//	List<commentVO> list(int bno);

	int delete(int cno);

	int update(commentVO cvo);

	PagingHandler list(int bno, PagingVO pgvo);

}

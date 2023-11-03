package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.commentVO;

public interface CommentService {

	int post(commentVO cvo);

	List<commentVO> list(int bno);

}

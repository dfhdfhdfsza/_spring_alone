package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.commentVO;

public interface CommentDAO {

	int post(commentVO cvo);

	List<commentVO> list(int bno);

}

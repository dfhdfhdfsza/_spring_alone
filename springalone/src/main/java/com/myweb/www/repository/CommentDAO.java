package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.commentVO;

public interface CommentDAO {

	int post(commentVO cvo);

//	List<commentVO> list(int bno);

	int delete(int cno);

	int update(commentVO cvo);

	List<commentVO> list(@Param("bno")int bno,@Param("pgvo") PagingVO pgvo);

	int totalCount(int bno);

}

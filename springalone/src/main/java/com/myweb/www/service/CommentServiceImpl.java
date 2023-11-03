package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.commentVO;
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

	@Override
	public List<commentVO> list(int bno) {
		return cdao.list(bno);
	}

}

package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.boardVO;
import com.myweb.www.domain.fileVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

@Service
public class BoardServiceImpl implements BoardService
{
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;
	
	@Inject
	private CommentDAO cdao;

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

//	@Override
//	public int modify(boardVO bvo) {
//		return bdao.modify(bvo);
//	}

	@Override
	public int remove(int bno) {
		int isOk=fdao.fileAllDel(bno);
		isOk*=cdao.comAllDel(bno);
		isOk*=bdao.remove(bno);
		return isOk;
	}

	@Override
	public int totalCount(PagingVO pgvo) {
		return bdao.totalCount(pgvo);
	}


	@Override
	public int registerFile(BoardDTO boardDTO) 
	{
		//bvo,flist 가져와서 각자 db에 저장
		//기존 메서드 활용
		int isOk=bdao.register(boardDTO.getBvo());
		
		//bvo insert 후, 파일도 있다면
		if(isOk>0 && boardDTO.getFlist().size()>0)
		{
			long bno=bdao.selectOneBno(); //가장 마지막에 등록된 bno
			//모든파일에 bno 세팅
			for(fileVO fvo:boardDTO.getFlist())
			{
				fvo.setBno(bno);
				isOk*=fdao.registerFile(fvo);
			}
		}
		return isOk;
	}

	@Override
	public List<fileVO> flist(int bno) {
		return fdao.flist(bno);
	}

	@Override
	public int fileDelete(String uuid) {
		
		return fdao.fileDelete(uuid);
	}

	@Override
	@Transactional
	public int modify(BoardDTO bdto) {
		int isOk=bdao.modify(bdto.getBvo());
		if(isOk>0&&bdto.getFlist().size()>0)
		{
			long bno= bdto.getBvo().getBno();
			for(fileVO fvo:bdto.getFlist())
			{
				fvo.setBno(bno);
				isOk*=fdao.registerFile(fvo);
			}
		}
		return isOk;
	}
	

	

}

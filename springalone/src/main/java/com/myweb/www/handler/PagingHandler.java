package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.commentVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingHandler 
{
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev,next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private List<commentVO> cmtList;
	
	
	public PagingHandler(PagingVO pgvo,int totalCount)
	{
		this.totalCount=totalCount;
		this.pgvo=pgvo;
		
		this.endPage=(int)Math.ceil(pgvo.getPageNo()/(double)pgvo.getQty())*pgvo.getQty();
		this.startPage=endPage-9;
		
		this.realEndPage=(int)Math.ceil(totalCount/(double)pgvo.getQty());
		if(endPage>realEndPage)
			endPage=realEndPage;
		
		this.next=endPage<realEndPage;
		this.prev=startPage>1;
	}
	public PagingHandler(PagingVO pgvo,int totalCount,List<commentVO> cmtList)
	{
		this(pgvo, totalCount);
		this.cmtList=cmtList;
	}
}

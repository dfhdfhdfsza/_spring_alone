package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO 
{
	private int pageNo;
	private int qty;
	
	private String keyword,type;
	
	public PagingVO()
	{
		this(1,10);
	}
	public PagingVO(int pageNo,int qty)
	{
		this.pageNo=pageNo;
		this.qty=qty;
	}
	public int getPageStart()
	{
		return (this.pageNo-1)*qty; 
	}
	//타입의 형태르르 여러가지 형태로 복합적인 검색을 하기 위해서
	//타입의 키워드 t,c,w,tc,tw,cw,tcw
	public String[] getTypeToArray()
	{
		return this.type==null? new String[] {}:this.type.split("");
	}
}

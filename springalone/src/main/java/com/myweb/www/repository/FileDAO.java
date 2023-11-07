package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.fileVO;

public interface FileDAO 
{

	int registerFile(fileVO fvo);

	List<fileVO> flist(int bno);

	int fileDelete(String uuid);

	int fileAllDel(int bno);

}

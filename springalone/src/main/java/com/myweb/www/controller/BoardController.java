package com.myweb.www.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.boardVO;
import com.myweb.www.domain.fileVO;
import com.myweb.www.handler.FIleHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/board/*")
public class BoardController 
{
	@Inject
	private BoardService bsv;
	
	@Inject
	private FIleHandler fh;
	
	private int isOk;
	
	@GetMapping("/register")
	public String register()
	{
		return "/board/register";
	}
//	@PostMapping("/register")
//	public String register(boardVO bvo,@RequestParam(name="files",required = false)MultipartFile[] files)
//	{
//		isOk=bsv.register(bvo);
//		return "index";
//	}
	
	@PostMapping("/register")
	public String register(boardVO bvo,@RequestParam(name="files",required = false)MultipartFile[] files)
	{
		isOk=bsv.register(bvo);
		List<fileVO>flist=new ArrayList<fileVO>();
		//file upload handler 생성
		if(files[0].getSize()>0)
		{
			flist=fh.uploadFiles(files);
		}
		log.info("flist는?:"+flist);
		isOk=bsv.registerFile(new BoardDTO(bvo,flist));
		
		return "index";
	}
//	@GetMapping("/list")
//	public String list(Model m)
//	{
//		List<boardVO>list=bsv.list();
//		m.addAttribute("list",list);
//		return "/board/list";
//	}
	@GetMapping("/list")
	public String list(Model m,PagingVO pgvo)
	{
		List<boardVO>list=bsv.list(pgvo);
		m.addAttribute("list",list);
		log.info("pgvo>>>"+pgvo);
		int totalCount=bsv.totalCount(pgvo);
		PagingHandler ph= new PagingHandler(pgvo, totalCount);
		log.info("페이징핸들러:"+ph);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	@GetMapping("/detail")
	public String detail(Model m,@RequestParam("bno")int bno)
	{
		BoardDTO bdto=new BoardDTO(bsv.detail(bno),bsv.flist(bno));
		m.addAttribute("bdto", bdto);
		return "/board/detail";
	}
	@GetMapping("/modify")
	public String modify(Model m,@RequestParam("bno")int bno)
	{
		BoardDTO bdto=new BoardDTO(bsv.detail(bno),bsv.flist(bno));
		m.addAttribute("bdto", bdto);
		return "/board/modify";
	}
//	@PostMapping("/modify")
//	public String modify(boardVO bvo)
//	{
//		isOk=bsv.modify(bvo);
//		return "redirect:/board/list";
//	}
	@PostMapping("/modify")
	public String modify(boardVO bvo,@RequestParam(name="files",required = false)MultipartFile[] files)
	{
		List<fileVO>flist=new ArrayList<>();
		if(files[0].getSize()>0)
			
		{
			flist=fh.uploadFiles(files);
		}
		BoardDTO bdto=new BoardDTO(bvo, flist);
		isOk=bsv.modify(bdto);
		return "redirect:/board/list";
	}
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno)
	{
		isOk=bsv.remove(bno);
		return "index";
	}
	@DeleteMapping(value = "/{uuid}")
	public ResponseEntity<String> fileDelete(@PathVariable("uuid")String uuid)
	{
		isOk=bsv.fileDelete(uuid);
		return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK):new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

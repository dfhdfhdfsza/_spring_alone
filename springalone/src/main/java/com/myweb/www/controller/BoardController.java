package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.boardVO;
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
	
	private int isOk;
	
	@GetMapping("/register")
	public String register()
	{
		return "/board/register";
	}
	@PostMapping("/register")
	public String register(boardVO bvo)
	{
		isOk=bsv.register(bvo);
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
		boardVO bvo=bsv.detail(bno);
		m.addAttribute("bvo", bvo);
		return "/board/detail";
	}
	@GetMapping("/modify")
	public String modify(Model m,@RequestParam("bno")int bno)
	{
		boardVO bvo=bsv.detail(bno);
		m.addAttribute("bvo", bvo);
		return "/board/modify";
	}
	@PostMapping("/modify")
	public String modify(boardVO bvo)
	{
		isOk=bsv.modify(bvo);
		return "redirect:/board/list";
	}
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")int bno)
	{
		isOk=bsv.remove(bno);
		return "index";
	}
}

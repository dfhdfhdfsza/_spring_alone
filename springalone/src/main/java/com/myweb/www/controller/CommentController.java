package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.commentVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/comment/")
public class CommentController 
{
	@Inject
	private CommentService csv;
	
	private int isOk;
	//consumes="application/json" => cvo로 받을때 필요
	@PostMapping(value="/post",consumes="application/json")
	public ResponseEntity<String> post(@RequestBody commentVO cvo)
	{
		log.info("js에서 받아온 cvo:"+cvo);
		isOk=csv.post(cvo);
		
		return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK):new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@GetMapping(value = "/{bno}",produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<commentVO>> list(@PathVariable("bno")int bno)
//	{
//		List<commentVO>list=csv.list(bno);
//		return new ResponseEntity<List<commentVO>>(list,HttpStatus.OK);0
//	}
	
	@GetMapping(value = "/{bno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno")int bno,@PathVariable("page")int page)
	{
		PagingVO pgvo=new PagingVO(page,5);
		PagingHandler ph=csv.list(bno,pgvo);
		return new ResponseEntity<PagingHandler>(ph,HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{cno}")
	public ResponseEntity<String> delete(@PathVariable("cno")int cno)
	{
		isOk=csv.delete(cno);
		return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK):new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@PutMapping(value = "/{cno}",consumes = "application/json",produces =MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> update(@RequestBody commentVO cvo)
	{
		log.info("update 들어옴");
		log.info("업데이트 cvo"+cvo);
		isOk=csv.update(cvo);
		return isOk>0 ? new ResponseEntity<String>("1",HttpStatus.OK):new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

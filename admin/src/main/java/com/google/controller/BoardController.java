package com.google.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.domain.BoardVO;
import com.google.domain.Criteria;
import com.google.domain.PageDTO;
import com.google.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {//Model-> view에 보내주는 작업
		model.addAttribute("list",service.getList(cri));
		int total =service.getListTotal();
		model.addAttribute("pageMaker",new PageDTO(cri, total));
	}
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board,RedirectAttributes rttr) {
		
		service.register(board);
		//board/list
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:/board/list";	
	}
	
	@GetMapping({"/get","/modify"})//같은 기능을할때는 중괄호로 묶어준다.
	public void get(@RequestParam("bno")long bno,Model model) {
		model.addAttribute("board",service.get(bno));
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")long bno) {
		
		service.remove(bno);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board,RedirectAttributes rttr) {
		
		service.modify(board);
		//board/list
		rttr.addFlashAttribute("result",board.getBno());
		
		return "redirect:/board/list";	
	}
}

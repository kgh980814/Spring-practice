package com.google.controller;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.domain.NewsVO;

import com.google.service.NewsService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/news/*")
public class NewsController {
	
	private NewsService service;
	
	@GetMapping("/list")
	public void list(Model model) {//Model-> view에 보내주는 작업
		model.addAttribute("list",service.getList());
	}
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(NewsVO news,RedirectAttributes rttr) {
		
		service.register(news);
		//board/list
		rttr.addFlashAttribute("result",news.getBno());
		
		return "redirect:/news/list";	
	}
	
	@GetMapping({"/get","/modify"})//같은 기능을할때는 중괄호로 묶어준다.
	public void get(@RequestParam("bno")long bno,Model model) {
		model.addAttribute("news",service.get(bno));
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")long bno) {
		
		service.remove(bno);
		
		return "redirect:/news/list";
	}
	
	@PostMapping("/modify")
	public String modify(NewsVO news,RedirectAttributes rttr) {
		
		service.modify(news);
		//board/list
		rttr.addFlashAttribute("result",news.getBno());
		
		return "redirect:/news/list";	
	}
}

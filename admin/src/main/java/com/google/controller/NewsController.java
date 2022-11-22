package com.google.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.domain.Criteria;
import com.google.domain.NewsVO;
import com.google.domain.PageDTO;
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
	public void list(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));

		int total = service.getListTotal(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, total ));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(NewsVO news, RedirectAttributes rttr) {
		
		service.register(news);
		
		// board/list로 이동하면서 result값(글번호)을 전달함.
		rttr.addFlashAttribute("result", news.getBno());
		
		return "redirect:/news/list";		
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") long bno, Model model) {
		model.addAttribute("news", service.get(bno));
	}
	
	/*
	 * @GetMapping("/modify") public void modify(@RequestParam("bno") long bno,
	 * Model model) { model.addAttribute("board", service.get(bno)); }
	 */
	
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") long bno) {
		
		service.remove(bno);
		
		return "redirect:/news/list";
		
	}
	@PostMapping("/modify")
	public String modify(NewsVO news, RedirectAttributes rttr) {
		
		service.modify(news);
		
		// board/list로 이동하면서 result값(글번호)을 전달함.
		rttr.addFlashAttribute("result", news.getBno());
		
		return "redirect:/news/list";		
	}
}

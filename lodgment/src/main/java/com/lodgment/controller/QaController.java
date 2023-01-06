package com.lodgment.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lodgment.annotation.LoginUser;
import com.lodgment.domain.User;
import com.lodgment.dto.QaDTO;
import com.lodgment.service.QaService;





@Controller
@SessionAttributes({"qaDto"})
public class QaController {

	@Autowired
	private QaService qaService;
	
	@GetMapping(path = "/qa")
	public String Qa(Model model) {
		// 화면에 문의내역을 출력시키기 위한 모든 정보 전달
		model.addAttribute("qas");
		
		return "qa/qa";
	}
	
	@GetMapping(path = "/qaDetail")
	public String QaDetail(@RequestParam("no") int no, Model model) {
		// 화면에 문의내역을 출력시키기 위한 모든 정보 전달
		model.addAttribute("qas", qaService.getQaByNo(no));
		model.addAttribute("qasByQaNo", qaService.getUserQaByQaNo(no));
		
		return "qa/detail";
	}
	
	@GetMapping(path = "/qaform")
	public String QaForm(Model model, QaDTO qaDto) {
		// 모델객체에 qa 저장 폼 담기
		model.addAttribute("qaDto", new QaDTO());

		return "qa/qaform";
	}
	
	@PostMapping(path = "/qacomplete")
	public String qaCompleted(@LoginUser User user,@ModelAttribute("qaDto") QaDTO qaDto, SessionStatus sessionStatus) throws IOException {
		
		qaDto.setUserNo(user.getNo());
		qaService.addNewQa(qaDto);
		
		sessionStatus.setComplete();
		
		return "qa/qacompleted";
	}
}

package com.lodgment.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lodgment.api.LodgmentAPI;
import com.lodgment.domain.ArroundVO;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/*")
@AllArgsConstructor
public class ArroundController {
	


	
	@GetMapping("/near")
	public String getSpotList(ArroundVO vo,@RequestParam(value="sigunguCode", defaultValue="") String sigunguCode, @RequestParam(value="contentTypeId",defaultValue="") String contentTypeId,Model model) throws Exception {
		
		
	
		model.addAttribute("sigunguCode",sigunguCode);
		model.addAttribute("contentTypeId",contentTypeId);
		log.info(""+vo);
		
		return "accommodation/home";
		
	}
}

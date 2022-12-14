package com.lodgment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lodgment.annotation.LoginUser;
import com.lodgment.domain.Category;
import com.lodgment.domain.User;
import com.lodgment.service.AccommodationService;
import com.lodgment.service.LoginedUserService;
import com.lodgment.service.ReservationService;
import com.lodgment.util.SessionUtils;

@SessionAttributes("userRegisterForm")
@Controller
@RequestMapping("/user")
public class UserinfoController {

	   @Autowired
	   private LoginedUserService loginedService;
	   @Autowired
	   private AccommodationService accommodationService;
	   @Autowired
	   private ReservationService reservationService;
	   /*
	    * @RequestParam
	    *       요청파라미터값을 매핑시키는 어노테이션이다.
	    *       name: 요청파라미터의 이름을 지정한다.
	    *       required: 필수 요청파라미터값인지 여부를 설정한다. 기본값은 true다. 
	    *               false로 지정하면 해당 요청파라미터값이 없어도 상관없다.
	    *       defaultValue: 요청파라미터값이 존재하지 않을 때 변수에 저장될 기본값을 지정한다.
	    */
	   @GetMapping(path = "/information")
	   public String home(@LoginUser User user, @RequestParam(name ="cat", required = false) String categoryId, String reservationNo, Model model) {
	      List<Category> categories = loginedService.getAllCategories();
	      model.addAttribute("categories",categories);
	      
	      if ("CAT_002".equals(categoryId)) {
			model.addAttribute("Readyreservation", reservationService.getReadytoReserveInfoByReserveId(user.getNo()));
			model.addAttribute("Refundreservation", reservationService.getRefundReserveInfoByReserveId(user.getNo()));
			model.addAttribute("payment",reservationService.getAllPaymentInfo(user.getNo()));
		
	      }
	      
	      if ("CAT_003".equals(categoryId)) {
	    	  int userNo = user.getNo();
	    	  model.addAttribute("likedAccommodations", accommodationService.getAllLikedItemsByUser(userNo));
	    	
	      }

	      return "user/information";
	   }
	   
	   @GetMapping(path = "/updateNickname")
	   public String updateNickname(@RequestParam(name = "nickname") String nickname) {
		   User user =  (User) SessionUtils.getAttribute("LOGIN_USER");
		   
		   user = loginedService.updateNickname(user.getId(), nickname);
		   SessionUtils.addAttribute("LOGIN_USER", user);
		   
		   return "redirect:/user/information?cat=CAT_001";
	   }
	   
	   @GetMapping(path = "/updateName")
	   public String updateName(@RequestParam(name = "name") String name) {
		   User user =  (User) SessionUtils.getAttribute("LOGIN_USER");
		   
		   user = loginedService.updateName(user.getId(), name);
		   SessionUtils.addAttribute("LOGIN_USER", user);
		   
		   return "redirect:/user/information?cat=CAT_001";
	   }
	   
	   @GetMapping(path = "/updateTel")
	   public String updateTel(@RequestParam(name = "tel") String tel) {
		   User user =  (User) SessionUtils.getAttribute("LOGIN_USER");
		   
		   user = loginedService.updateTel(user.getId(), tel);
		   SessionUtils.addAttribute("LOGIN_USER", user);
		   
		   return "redirect:/user/information?cat=CAT_001";
	   }
	 
	   @GetMapping(path = "/changePassword")
	   public String changePassword(@LoginUser User user, @RequestParam(name ="cat", required = false) String categoryId, String reservationNo, Model model) {
		   List<Category> categories = loginedService.getAllCategories();
		   model.addAttribute("categories",categories);
		   return "user/changePassword";
	   }
	   
	   @GetMapping(path = "/pwUpdate")
	   public String pwUpdate(@LoginUser User user, @RequestParam(name ="pwUpdateForm") String password) {
		   
		   user = loginedService.updatePassword(user.getId(), password);
		   SessionUtils.addAttribute("LOGIN_USER", user);
		   
		   return "user/changePassword";
	   }
}

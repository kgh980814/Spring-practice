package com.lodgment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lodgment.domain.User;
import com.lodgment.mapper.UserMapper;
import com.lodgment.util.MailUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 카카오 로그인으로 획득한 사용자정보로 로그인처리를 수행한다.<p>
	 * 카카오 로그인은 회원가입 절차없이 카카오 로그인 API로 획득한 정보를 데이터베이스에 저장한다.<p>
	 * 카카오 로그인으로 우리 서비스를 한 번이라도 사용한 사용자는 사용자 정보가 데이터베이스에 이미 저장되어 있다.
	 * @param user 카카오 로그인으로 획득한 사용자 정보
	 * @return 사용자 정보
	 */
	public User loginWithKakao(User user) {
		User savedUser = userMapper.getUserByEmail(user.getEmail());
		log.info("카카오 로그인 아이디로 조회한 유저 정보: " + savedUser);
		
		if (savedUser == null) {
			user.setId(UUID.randomUUID().toString());
			userMapper.insert(user);
			log.info("카카오 로그인 신규 사용자 정보 등록 완료: " + user.getId() + ", " + user.getName());
		}
		return savedUser;
	}
	
	/**
	 * 페이스북 로그인으로 획득한 사용자정보로 로그인처리를 수행한다.
	 * 페이스북 로그인은 회원가입 절차없이 페이스북 로그인 API로 획득한 정보가 데이터베이스에 저장된다.
	 * 페이스북 로그인으로 서비스를 한 번이라도 사용한 사용자는 사용자 정보가 데이터베이스에 이미 저장되어 있다.
	 * @param user 페이스북 로그인한 사용자 정보
	 * @return 사용자 정보
	 */
	public User loginWithFacebook(User user) {
		User savedUser = userMapper.getUserById(user.getId());
		log.info("페이스북 로그인 이름으로 조회한 유저 정보: " + savedUser);
		
		if (savedUser == null) {
			user.setId(UUID.randomUUID().toString());
			userMapper.insert(user);// User의 no
			log.info("페이스북 로그인 신규 사용자 정보 등록 완료: " + user.getId() + ", " + user.getName());
		}
		
		return savedUser;
	}
	
	/**
	 * 네이버 로그인으로 획득한 사용자정보로 로그인처리를 수행한다.
	 * 네이버 로그인은 회원가입 절차없이 페이스북 로그인 API로 획득한 정보가 데이터베이스에 저장된다.
	 * 네이버 로그인으로 서비스를 한 번이라도 사용한 사용자는 사용자 정보가 데이터베이스에 이미 저장되어 있다.
	 * @param user 네이버 로그인한 사용자 정보
	 * @return 사용자 정보
	 */
	public User loginWithNaver(User user) {
		User savedUser = userMapper.getUserByEmail(user.getEmail());
		log.info("네이버 로그인 아이디로 조회한 유저 정보: " + savedUser);
		
		if (savedUser == null) {
			user.setId(UUID.randomUUID().toString());
			userMapper.insert(user);// User의 no
			log.info("네이버 로그인 신규 사용자 정보 등록 완료: " + user.getId() + ", " + user.getName());
		}
		
		return savedUser;
	}
	
	/**
	 * 이 사이트의 회원가입 폼에서 입력한 사용자 정보를 데이터베이스에 등록시킨다.<p>
	 * 사용자명(사용자 아이디), 이메일 중복 여부를 체크한다.
	 * @param user 신규 사용자 정보
	 * @return 사용자정보
	 */
	public User registerUser(User user) {
		log.info("일반 회원가입 유저정보: " + user);
		
		User savedUser = userMapper.getUserById(user.getId());
		if (savedUser != null) {
			log.warn("일반 회원가입: 아이디 중복");
			throw new RuntimeException("중복된 아이디입니다.");
		}
		
		savedUser = userMapper.getUserByEmail(user.getEmail());
		if (savedUser != null) {
			log.warn("일반 회원가입: 이메일 중복");
			throw new RuntimeException("중복된 이메일 주소입니다.");
		}
		
		userMapper.insert(user);
		log.info("일반 회원가입 신규 사용자 정보 등록 완료");
		
		return user;
	}
	
	/**
	 * 이 사이트에 회원가입한 사용자의 아이디, 비밀번호를 전달받아서 로그인 처리를 수행한다.
	 * @param username 사용자명(아이디)
	 * @param password 비밀번호
	 * @return 인증된 사용자 정보
	 */
	public User login(String id, String password) {
		log.info("일반 로그인: " + id);
		
		User savedUser = userMapper.getUserById(id);
		if (savedUser == null) {
			log.warn("일반 로그인: 사용자정보 없음");
			throw new RuntimeException("사용자가 존재하지 않습니다.");
		}
		if (!"normal".equals(savedUser.getLoginType())) {
			log.warn("일반 로그인: 회원가입 사용자 아님");
			throw new RuntimeException("회원가입으로 가입한 사용자가 아닙니다.");
		}
		if ("Y".equals(savedUser.getDisabled())) {
			log.warn("일반 로그인: 탈퇴처리된 사용자");
			throw new RuntimeException("탈퇴처리된 사용자입니다.");
		}
		if (!password.equals(savedUser.getPassword())) {
			log.warn("일반 로그인: 비밀번호 불일치");
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		return savedUser;
	}

	/**
	 * 사용자 아이디 찾기
	 * @param name 사용자 이름
	 * @param email	사용자 이메일
	 * @return 사용자 아이디
	 */
	public String findId(String name, String email) {
		String findId = userMapper.findId(name, email);
		return findId;
	}

	public String findPw(String id, String email) throws Exception {
		
		String result = null;
	      
	    System.out.println("email 확인 : " + email);
	    //아이디&이메일 정보 확인
	    int check = userMapper.finePwCheck(id, email);
	    //회원정보 불러오기
	    User user = userMapper.getUserByEmail(email);
	      
	    //가입된 이메일이 존재한다면 이메일 발송
	    if(check == 1) {
	         
	       //임시 비밀번호 생성(UUID 이용 - 특수문자는 못넣음 ㅠㅠ)
	       String tempPw = UUID.randomUUID().toString().replace("-", ""); // -를 제거
	       tempPw = tempPw.substring(0,10); //tempPw를 앞에서부터 10자리 잘라줌
	         
	       System.out.println("임시 비밀번호 확인 : " + tempPw);
	         
	       //user객체에 임시 비밀번호 담기
	       user.setPassword(tempPw);
	         
	       //메일 전송
	       MailUtils mail = new MailUtils();
	       mail.sendMail(user);
	         
	      //비밀번호 변경
	      userMapper.updatePw(user);
	         
	      result = "success";
	   } else {
	      result = "fail";
	   }
	   return result;

	}
	
}

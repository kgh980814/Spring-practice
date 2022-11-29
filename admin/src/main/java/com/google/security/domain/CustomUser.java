package com.google.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.google.domain.MemberVO;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(),vo.getUserpw(),vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList()));//부모에있는 생성자 호출
		this.member =vo;// 이 메소드를 memberVO에 넣겠다.
	}

}
